package cc.codedhyan.codeitup.testCase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class getCase {
    @Value("${application.security.github.pat}")
    private String PAT_KEY;

    @Test
    public void getRawFileAsJson () throws JsonProcessingException {
        String raw = "https://raw.githubusercontent.com/Bijit-Mondal/codeitup-problems/main/two_sum/test_cases.json";
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
        for(int i = testCases.size()-1; i >= 0; i--) {
            JsonNode testCase = testCases.get(i);
            String input = testCase.get("input").asText();
            String output = testCase.get("output").asText();

            // Print the input and output
            System.out.println("Input: " + input + ", Output: " + output);
        }
    }

}
