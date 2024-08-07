package cc.codedhyan.codeitup.problem;

import cc.codedhyan.codeitup.problem.model.SubmissionResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionListResponse {
    private String submissionId;
    private String profile;
    private SubmissionResult submissionResult;
    private String language;
    private OffsetDateTime time;
}