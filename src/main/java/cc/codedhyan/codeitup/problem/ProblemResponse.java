package cc.codedhyan.codeitup.problem;

import cc.codedhyan.codeitup.problem.model.DefaultCode;
import cc.codedhyan.codeitup.problem.model.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProblemResponse {
    private String id;
    private String title;
    private String slug;
    private String description;
    private String testCases;
    private Difficulty difficulty;
    private Boolean hidden;
    private Long solved;
    private String solutionGist;
    private String solutionTutorial;
    private List<DefaultCode> defaultCode;
}
