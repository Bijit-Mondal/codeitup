package cc.codedhyan.codeitup.problem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionResponse {
    private String submissionId;
    private String message;
}
