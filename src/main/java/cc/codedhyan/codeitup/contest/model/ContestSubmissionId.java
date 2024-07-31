package cc.codedhyan.codeitup.contest.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ContestSubmissionId implements Serializable {
//    private String id;
    private String contestId;
    private String problemId;
    private String userId;
}