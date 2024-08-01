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
public class DefaultCodeRequest {
    @NotNull(message = "Language ID can't be null")
    private Integer languageId;

    @NotNull(message = "Problem ID can't be null")
    @NotBlank(message = "Problem ID can't be blank")
    private String problemId;

    @NotBlank(message = "Code can't be blank")
    @NotNull(message = "Code can't be null")
    private String code;
}
