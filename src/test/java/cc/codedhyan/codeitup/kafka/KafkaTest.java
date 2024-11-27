package cc.codedhyan.codeitup.kafka;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import cc.codedhyan.codeitup.problem.Judge0Response;
import cc.codedhyan.codeitup.problem.Judge0Status;

import org.springframework.core.io.Resource;

@SpringBootTest
public class KafkaTest {

    @Autowired
    private KafkaTemplate<String, Judge0Response> kafkaTemplate;

    @Test
    public void processSubmissionCallback() {
        Judge0Response response = Judge0Response.builder()
            .memory(10)
            .message("Passed")
            .status(Judge0Status.builder().id(2).description("Hey").build())
            .build();
        kafkaTemplate.send("problem-submission-webhook", response);        
    }
}
