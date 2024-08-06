package cc.codedhyan.codeitup.testjudge;

import cc.codedhyan.codeitup.problem.Judge0Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@RequiredArgsConstructor
@SpringBootTest
public class test {
    @Value("${application.security.rapidapi.key}")
    private String RAPIDAPI_KEY;

    @Value("${application.security.rapidapi.host}")
    private String RAPIDAPI_HOST;

    @Test
    public void submission () {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-rapidapi-key",RAPIDAPI_KEY);
        headers.set("x-rapidapi-host",RAPIDAPI_HOST);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.put("language_id", 62);
        String code = """
            class Main{
                public static void main(String[]args){
                    System.out.println("Hello, World!");
                }
            }
        """;
        rootNode.put("source_code", code);
        rootNode.put("expected_output", "Hello, World");
        String requestBody = rootNode.toString();
        System.out.println(requestBody);
        HttpEntity<?> entity = new HttpEntity<>(requestBody,headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<?> res = restTemplate.exchange(
                "https://judge0-ce.p.rapidapi.com/submissions/?base64_encoded=false",
                org.springframework.http.HttpMethod.POST,
                entity,
                String.class
        );
        System.out.println(res);
//        {"token":"3bd3faa1-674d-4ebb-82f0-0153579c0c8a"}
//        {"token":"77bc8875-a2ef-4090-970d-acf82943a9b2"}
//        {"token":"2feeb058-4ae8-4309-8eed-b7f7962e5b46"}
//        {"token":"880da855-59a1-4bb9-b22f-0d85d8a3f70c"}
//        bbb2f2ec-e33c-4b3b-b727-930b4e2bdf0d
    }
    @Test
    public void getSubmission() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-rapidapi-key",RAPIDAPI_KEY);
        headers.set("x-rapidapi-host",RAPIDAPI_HOST);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        String token = "8c88aa7e-f6af-4931-a6fc-6b18843af7ac";

        RestTemplate restTemplate = new RestTemplate();
        Judge0Response res = restTemplate.exchange(
                "https://judge0-ce.p.rapidapi.com/submissions/"+token+"?base64_encoded=false",
                org.springframework.http.HttpMethod.GET,
                entity,
                Judge0Response.class
        ).getBody();
        System.out.println(res);
//        {"language_id":62,"stdout":null,"status_id":6,"stderr":null}
    }


}
