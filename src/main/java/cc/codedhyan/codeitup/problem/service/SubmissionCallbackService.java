 package cc.codedhyan.codeitup.problem.service;

 import cc.codedhyan.codeitup.config.constants.KafkaTopics;
 import cc.codedhyan.codeitup.exception.ApiRequestExceptionNotFound;
 import cc.codedhyan.codeitup.problem.Judge0Response;
 import cc.codedhyan.codeitup.problem.model.Submission;
 import cc.codedhyan.codeitup.problem.model.SubmissionResult;
 import cc.codedhyan.codeitup.problem.model.TestCases;
 import cc.codedhyan.codeitup.problem.model.TestCasesResult;
 import cc.codedhyan.codeitup.problem.repository.SubmissionRepository;
 import cc.codedhyan.codeitup.problem.repository.TestCasesRepository;
 import lombok.RequiredArgsConstructor;
 import lombok.extern.slf4j.Slf4j;

 import org.springframework.kafka.annotation.KafkaListener;
 import org.springframework.kafka.core.KafkaTemplate;
 import org.springframework.kafka.support.Acknowledgment;
 import org.springframework.stereotype.Service;

 import java.util.List;

 @Service
 @Slf4j
 @RequiredArgsConstructor
 public class SubmissionCallbackService {
     private final KafkaTemplate<String, Judge0Response> kafkaTemplate;
     private final TestCasesRepository testCasesRepository;
     private final SubmissionRepository submissionRepository;

     public void processSubmissionCallback(Judge0Response response) {
         try {
             kafkaTemplate.send(KafkaTopics.PROBLEM_SUBMISSION_WEBHOOK_TOPIC, response);
         } catch (Exception e) {
             log.error("Failed to send Kafka message for token: {}", response.getToken(), e);
         }
     }

     @KafkaListener(topics = KafkaTopics.PROBLEM_SUBMISSION_WEBHOOK_TOPIC, groupId = "submission-group")
     public void consumeSubmissionCallback(Judge0Response response){
         TestCases testCase = testCasesRepository.findByJudge0TrackingId(response.getToken())
                 .orElseThrow(() ->  new ApiRequestExceptionNotFound("Test case not found"));
         testCase.setTestCasesResult(outputMapping(response.getStatus().getDescription()));
         testCase.setTime(response.getTime());
         testCase.setMemory(response.getMemory());
         testCasesRepository.save(testCase);

         List<TestCases> allTestCaseData = testCasesRepository.findBySubmissionId(testCase.getSubmissionId());
         List<TestCases> pendingTestCases = allTestCaseData.stream()
                 .filter(tc -> tc.getTestCasesResult() == TestCasesResult.PENDING).toList();

         List<TestCases> failedTestCases = allTestCaseData.stream()
                 .filter(tc -> tc.getTestCasesResult() != TestCasesResult.ACCEPTED).toList();
         log.info("Pending test cases: {}", pendingTestCases.size());
         log.info("Failed test cases: {}", failedTestCases.size());
         log.info("All test cases: {}", allTestCaseData.size());
         if (pendingTestCases.size() == 0) {
             boolean accepted = failedTestCases.size() == 0;
             Submission submission = submissionRepository.findById(testCase.getSubmissionId())
                     .orElseThrow(() -> new ApiRequestExceptionNotFound("Submission not found"));

             submission.setSubmissionResult(accepted ? SubmissionResult.ACCEPTED : SubmissionResult.REJECTED);
             Double maxTime = allTestCaseData.stream()
             .mapToDouble(tc -> {
                 try {
                     return Double.parseDouble(tc.getTime());
                 } catch (NumberFormatException e) {
                     log.warn("Invalid time format for test case {}: {}", tc.getId(), tc.getTime());
                     return 0;
                 }
             })
             .max()
             .orElse(0);

             Integer maxMemory = allTestCaseData.stream()
             .mapToInt(tc -> tc.getMemory() == null ? 0 : tc.getMemory())
             .max()
             .orElse(0);

             submission.setTime(maxTime);
             submission.setMemory(maxMemory);


             log.info("Submission result: {}", submission);
             submissionRepository.save(submission);
         }
         // acknowledgment.acknowledge();
     }

     private TestCasesResult outputMapping(String statusDescription) {
         // Map Judge0 status description to TestCasesResult enum
         return switch (statusDescription) {
             case "Accepted" -> TestCasesResult.ACCEPTED;
             case "Wrong Answer" -> TestCasesResult.WRONG_ANSWER;
             case "Time Limit Exceeded" -> TestCasesResult.TIME_LIMIT_EXCEEDED;
             case "Compilation Error" -> TestCasesResult.COMPILATION_ERROR;
             case "Runtime Error (NZEC)" -> TestCasesResult.RUNTIME_ERROR;
             case "Internal Error" -> TestCasesResult.INTERNAL_ERROR;
             // Add other cases as necessary
             default -> TestCasesResult.PENDING;
         };
     }

 }
