package cc.codedhyan.codeitup.problem;

import cc.codedhyan.codeitup.problem.model.Difficulty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProblemRequest {
    @NotBlank(message = "Title can't be blank")
    @NotNull(message = "Title can't be null")
    private String title;

    @NotBlank(message = "Slug can't be blank")
    @NotNull(message = "Slug can't be null")
    private String slug;

    @NotBlank(message = "Description can't be blank")
    @NotNull(message = "Description can't be null")
    private String description;

    @NotBlank(message = "Test cases can't be blank")
    @NotNull(message = "Test cases can't be null")
    private String testCases;

    @NotNull(message = "Difficulty can't be null")
    private Difficulty difficulty;

    private String solutionGist;
    private String solutionTutorial;
}
