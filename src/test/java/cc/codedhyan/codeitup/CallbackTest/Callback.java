package cc.codedhyan.codeitup.CallbackTest;

import cc.codedhyan.codeitup.exception.ApiRequestExceptionNotFound;
import cc.codedhyan.codeitup.problem.Judge0Response;
import cc.codedhyan.codeitup.problem.model.Submission;
import cc.codedhyan.codeitup.problem.model.SubmissionResult;
import cc.codedhyan.codeitup.problem.model.TestCases;
import cc.codedhyan.codeitup.problem.model.TestCasesResult;
import cc.codedhyan.codeitup.problem.repository.SubmissionRepository;
import cc.codedhyan.codeitup.problem.repository.TestCasesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class Callback {

    @Autowired
    private TestCasesRepository testCasesRepository;

    @Autowired
    private SubmissionRepository submissionRepository;

    @Test
    public void testCallback() {
        Judge0Response response = new Judge0Response();
        response.setMemory(100);
        List<TestCases> allTestCaseData = testCasesRepository.findBySubmissionId("a66412f3-e8c5-4994-a818-9879d928f849");
        List<TestCases> pendingTestCases = allTestCaseData.stream()
                .filter(tc -> tc.getTestCasesResult() == TestCasesResult.PENDING).toList();

        List<TestCases> failedTestCases = allTestCaseData.stream()
                .filter(tc -> tc.getTestCasesResult() != TestCasesResult.ACCEPTED).toList();

        System.out.println("Pending Test Cases: " + pendingTestCases);
        System.out.println("Failed Test Cases: " + failedTestCases);

        if (pendingTestCases.isEmpty()) {
            boolean accepted = failedTestCases.isEmpty();
            Submission submission = submissionRepository.findById("a66412f3-e8c5-4994-a818-9879d928f849")
                    .orElseThrow(() -> new ApiRequestExceptionNotFound("Submission not found"));
            System.out.println(accepted);
            submission.setSubmissionResult(accepted ? SubmissionResult.ACCEPTED : SubmissionResult.REJECTED);
            submission.setMemory(allTestCaseData.stream().mapToInt(TestCases::getMemory).max().orElse(0));
            submission.setTime(allTestCaseData.stream()
                    .mapToDouble(tc -> Double.parseDouble(tc.getTime()))
                    .max().orElse(0));
            submissionRepository.save(submission);
        }
    }
}
