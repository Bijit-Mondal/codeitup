package cc.codedhyan.codeitup.problem.service;

import cc.codedhyan.codeitup.exception.ApiInternalServerErrorException;
import cc.codedhyan.codeitup.exception.ApiRequestExceptionNotFound;
import cc.codedhyan.codeitup.problem.SubmissionRequest;
import cc.codedhyan.codeitup.problem.SubmissionResponse;
import cc.codedhyan.codeitup.problem.model.DefaultCode;
import cc.codedhyan.codeitup.problem.model.Language;
import cc.codedhyan.codeitup.problem.model.Problem;
import cc.codedhyan.codeitup.problem.repository.DefaultCodeRepository;
import cc.codedhyan.codeitup.problem.repository.LanguageRepository;
import cc.codedhyan.codeitup.problem.repository.ProblemRepository;
import cc.codedhyan.codeitup.problem.repository.SubmissionRepository;
import cc.codedhyan.codeitup.user.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    public SubmissionResponse createSubmission(SubmissionRequest request) {
        User user =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Problem problem = problemRepository.findBySlugAndHiddenFalse(request.getProblemSlug())
                .orElseThrow(() -> new ApiRequestExceptionNotFound("Problem not found"));
        DefaultCode defaultCode = defaultCodeRepository.findByProblemIdAndLanguageId(problem.getId(), request.getLanguageId())
                .orElseThrow(() -> new ApiRequestExceptionNotFound("Default code for this problem not found"));
        Language language = languageRepository.findById(request.getLanguageId())
                .orElseThrow(() -> new ApiRequestExceptionNotFound("Language not found"));
        String fullCode = defaultCode.getRunnerCode().replace("##USER_CODE##", request.getCode());
        ObjectNode judge0SubmissionJson = createJudge0ApiNode(problem.getTestCases(), language.getJudge0id(), fullCode);

        return null;
    }

    private ObjectNode createJudge0ApiNode(String testCasesURL, Integer judge0id, String fullCode) {
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
        }
        return judge0ApiNode;
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
}