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
public class LanguageRequest {
    @NotBlank(message = "Name can't be blank")
    @NotNull(message = "Name can't be null")
    private String name;

    @NotNull(message = "judge0 id can't be null")
    private Integer judge0id;
}