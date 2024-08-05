package cc.codedhyan.codeitup.problem;

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
public class SubmissionRequest {
    @NotBlank(message = "Code can't be blank")
    @NotNull(message = "Code can't be null")
    private String code;

    @NotNull(message = "Language id can't be null")
    private Integer languageId;

    @NotBlank(message = "Problem id can't be blank")
    @NotNull(message = "Problem id can't be null")
    private String problemSlug;
}