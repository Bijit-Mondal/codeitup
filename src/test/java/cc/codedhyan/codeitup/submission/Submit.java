package cc.codedhyan.codeitup.submission;

import cc.codedhyan.codeitup.exception.ApiRequestExceptionNotFound;
import cc.codedhyan.codeitup.problem.model.DefaultCode;
import cc.codedhyan.codeitup.problem.model.Problem;
import cc.codedhyan.codeitup.problem.repository.DefaultCodeRepository;
import cc.codedhyan.codeitup.problem.repository.ProblemRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class Submit {
    @Value("${application.security.rapidapi.key}")
    private String RAPIDAPI_KEY;

    @Value("${application.security.rapidapi.host}")
    private String RAPIDAPI_HOST;

    @Value("${application.security.github.pat}")
    private String PAT_KEY;

    @Autowired
    private DefaultCodeRepository defaultCodeRepository;

    @Autowired
    private ProblemRepository problemRepository;

    @Test
    public void createSubmission() throws JsonProcessingException {
        Problem problem = problemRepository.findBySlugAndHiddenFalse("n-meetings")
                .orElseThrow(() -> new RuntimeException("Problem not found"));
        DefaultCode defaultCode = defaultCodeRepository.findByProblemIdAndLanguageId(problem.getId(), 1)
                .orElseThrow(() -> new RuntimeException("Default code for this problem not found"));
        String code = """
                class Solution {
                     // Function to find the maximum number of meetings that can
                     // be performed in a meeting room.
                     public int maxMeetings(int n, int start[], int end[]) {
                         ArrayList<Pair> pairs = new ArrayList<>(n);
                         for(int i=0;i<n;i++) {
                             pairs.add(new Pair(start[i], end[i]));
                         }
                         Collections.sort(pairs, (Comparator.comparingInt(p -> p.end)));
                         int lastEnd = -1, count = 0;
                         for(Pair pair : pairs) {
                             if(pair.start > lastEnd) {
                                 count++;
                                 lastEnd = pair.end;
                             }
                         }
                         return count;
                     }
                 }
                 
                 class Pair{
                     int start, end;
                     Pair(int start, int end) {
                         this.start = start;
                         this.end = end;
                     }
                 }
                """;
        String runnerCode = defaultCode.getRunnerCode().replace("##USER_CODE##", code);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-rapidapi-key",RAPIDAPI_KEY);
        headers.set("x-rapidapi-host",RAPIDAPI_HOST);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode judge0ApiNode = mapper.createObjectNode();
        ArrayNode submissionsNode = judge0ApiNode.putArray("submissions");

        JsonNode testCases = getRawFileAsJson();
        for(int i = testCases.size()-1; i >= 0; i--) {
            JsonNode testCase = testCases.get(i);
            String input = testCase.get("input").asText();
            String output = testCase.get("output").asText();

            ObjectNode submissionNode = submissionsNode.addObject();
            submissionNode.put("language_id", 62);
            submissionNode.put("source_code", runnerCode);
            submissionNode.put("stdin", input);
            submissionNode.put("expected_output", output);
        }

        String requestBody = judge0ApiNode.toString();
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                "https://judge0-ce.p.rapidapi.com/submissions/batch?base64_encoded=false",
                HttpMethod.POST,
                request,
                String.class
        );

        // Parse the response body to extract tokens
        JsonNode responseBody = mapper.readTree(response.getBody());
        ArrayNode tokensArray = (ArrayNode) responseBody;

        for (JsonNode tokenNode : tokensArray) {
            String token = tokenNode.get("token").asText();
            System.out.println("Token: " + token);
        }
//        Token: e9e23190-a210-4053-8697-40770b976de1
//        Token: f94555ab-22fb-4e51-8716-11e7cdb6a458
    }

    @Test
    public JsonNode getRawFileAsJson () throws JsonProcessingException {
        String raw = "https://raw.githubusercontent.com/Bijit-Mondal/codeitup-problems/main/n-meetings/test_cases.json";
        // get the file as json object use the raw url and jackson library
        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Set the Authorization header with the PAT
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(PAT_KEY);

        // Create a RequestEntity with the URL, headers, and HTTP method
        HttpEntity<?> entity = new HttpEntity<>(headers);


        // Send the request and get the response
        ResponseEntity<String> response = restTemplate.exchange(
                raw,
                HttpMethod.GET,
                entity,
                String.class
        );

        // Get the JSON content from the response
        String jsonContent = response.getBody();

        // Use Jackson to parse the JSON content
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonContent);

        // Now you can access the JSON data
        System.out.println(jsonNode);

//        This is the response {"problem_name":"two_sum","test_cases":[{"input":"5002 -2","output":"5000"},{"input":"60 80","output":"140"},{"input":"10 20","output":"30"}]}
//        Now iterate through the test cases and print input - ___ output - ___
        // Get the test cases array
        JsonNode testCases = jsonNode.get("test_cases");

        // Iterate through the test cases
//        for (JsonNode testCase : testCases) {
//            String input = testCase.get("input").asText();
//            String output = testCase.get("output").asText();
//
//            // Print the input and output
//            System.out.println("Input: " + input + ", Output: " + output);
//        }
//        using for loop
//        for(int i = testCases.size()-1; i >= 0; i--) {
//            JsonNode testCase = testCases.get(i);
//            String input = testCase.get("input").asText();
//            String output = testCase.get("output").asText();
//
//            // Print the input and output
//            System.out.println("Input: " + input + ", Output: " + output);
//        }
        return testCases;
    }
}
