package cc.codedhyan.codeitup.problem.service;

import cc.codedhyan.codeitup.exception.ApiInternalServerErrorException;
import cc.codedhyan.codeitup.exception.ApiRequestExceptionNotFound;
import cc.codedhyan.codeitup.problem.SubmissionRequest;
import cc.codedhyan.codeitup.problem.SubmissionResponse;
import cc.codedhyan.codeitup.problem.model.*;
import cc.codedhyan.codeitup.problem.repository.*;
import cc.codedhyan.codeitup.user.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubmissionService {
    @Value("${application.security.github.pat}")
    private String PAT_KEY;
    @Value("${application.security.rapidapi.key}")
    private String RAPIDAPI_KEY;
    @Value("${application.security.rapidapi.host}")
    private String RAPIDAPI_HOST;

    private final DefaultCodeRepository defaultCodeRepository;
    private final SubmissionRepository submissionRepository;
    private final LanguageRepository languageRepository;
    private final ProblemRepository problemRepository;
    private final TestCasesRepository testCasesRepository;

    public SubmissionResponse createSubmission(SubmissionRequest request) {
        User user =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Problem problem = problemRepository.findBySlugAndHiddenFalse(request.getProblemSlug())
                .orElseThrow(() -> new ApiRequestExceptionNotFound("Problem not found"));
        DefaultCode defaultCode = defaultCodeRepository.findByProblemIdAndLanguageId(problem.getId(), request.getLanguageId())
                .orElseThrow(() -> new ApiRequestExceptionNotFound("Default code for this problem not found"));
        Language language = languageRepository.findById(request.getLanguageId())
                .orElseThrow(() -> new ApiRequestExceptionNotFound("Language not found"));
        String fullCode = defaultCode.getRunnerCode().replace("##USER_CODE##", request.getCode());
        String judge0SubmissionRequest = createJudge0ApiNode(problem.getTestCases(), language.getJudge0id(), fullCode);
        String[] tokens = submitToJudge0(judge0SubmissionRequest);
        Submission submission = Submission.builder()
                .userId(user.getUser_id())
                .problemId(problem.getId())
                .languageId(request.getLanguageId())
                .submissionResult(SubmissionResult.PENDING)
                .code(request.getCode())
                .fullCode(fullCode)
                .build();
        Submission savedSubmission = submissionRepository.save(submission);
        List<TestCases> testCases = new ArrayList<>();
        for(int i=0;i<tokens.length;i++){
            TestCases tc = TestCases.builder()
                    .submissionId(savedSubmission.getId())
                    .judge0TrackingId(tokens[i])
                    .testCasesResult(TestCasesResult.PENDING)
                    .ind(i)
                    .build();
            testCases.add(tc);
        }
        testCasesRepository.saveAll(testCases);
        return SubmissionResponse.builder()
                .submissionId(savedSubmission.getId())
                .message("Submission made successfully")
                .build();
    }

    private String[] submitToJudge0(String judge0SubmissionRequest){
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("X-Auth-Token",RAPIDAPI_KEY);
            HttpEntity<String> entity = new HttpEntity<>(judge0SubmissionRequest, headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    "http://"+RAPIDAPI_HOST+"/submissions/batch?base64_encoded=false",
                    HttpMethod.POST,
                    entity,
                    String.class
            );
            ObjectMapper mapper = new ObjectMapper();
            JsonNode responseBody = mapper.readTree(response.getBody());
            ArrayNode tokensArray = (ArrayNode) responseBody;
            String[] tokens = new String[tokensArray.size()];
            for (int i = 0; i < tokensArray.size(); i++) {
                tokens[i] = tokensArray.get(i).get("token").asText();
            }
            return tokens;
        }catch (JsonProcessingException e) {
            throw new ApiInternalServerErrorException("Error while parsing response from Judge0",e);
        }
    }

    private String createJudge0ApiNode(String testCasesURL, Integer judge0id, String fullCode) {
        JsonNode testCases = getTestCases(testCasesURL);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode judge0ApiNode = mapper.createObjectNode();
        ArrayNode submissionsNode = judge0ApiNode.putArray("submissions");

        for (int i = testCases.size() - 1; i >= 0; i--) {
            JsonNode testCase = testCases.get(i);
            String input = testCase.get("input").asText();
            String output = testCase.get("output").asText();

            ObjectNode submissionNode = submissionsNode.addObject();
            submissionNode.put("language_id", judge0id);
            submissionNode.put("source_code", fullCode);
            submissionNode.put("stdin", input);
            submissionNode.put("expected_output", output);
            submissionNode.put("callback_url","http://45.79.123.242:8080/api/v1/open/submission-callback");
        }
        return judge0ApiNode.toString();
    }

    private JsonNode getTestCases (String raw) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(PAT_KEY);
            HttpEntity<?> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    raw,
                    HttpMethod.GET,
                    entity,
                    String.class
            );
            String jsonContent = response.getBody();
            // Use Jackson to parse the JSON content
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonContent);
            return jsonNode.get("test_cases");
        } catch (JsonProcessingException e) {
            throw new ApiInternalServerErrorException("Error while parsing test cases",e);
        }
    }

    public Submission getSubmission(String submissionId) {
        return submissionRepository.findById(submissionId)
                .orElseThrow(() -> new ApiRequestExceptionNotFound("Submission not found"));
    }
}