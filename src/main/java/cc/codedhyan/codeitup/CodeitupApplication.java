package cc.codedhyan.codeitup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CodeitupApplication {

	public static void main(String[] args){
		SpringApplication.run(CodeitupApplication.class, args);
	}
}
