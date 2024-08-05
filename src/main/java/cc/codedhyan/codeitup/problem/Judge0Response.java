package cc.codedhyan.codeitup.problem;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Judge0Response {
    private String stdout;
    private String time;
    private int memory;
    private String stderr;
    private String token;
    private String compile_output;
    private String message;
    private Judge0Status status;

    // Getters and setters
}

