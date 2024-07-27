package cc.codedhyan.codeitup.auth;

import jakarta.validation.constraints.Email;
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
public class RegisterRequest {
    @NotBlank(message = "Email can't be blank")
    @NotNull(message = "Email can't be null")
    @Email
    private String email;

    @NotBlank(message = "password can't be blank")
    @NotNull(message = "password can't be null")
    private String password;

    @NotBlank(message = "username can't be blank")
    @NotNull(message = "username can't be null")
    private String profileName;

    @NotBlank(message = "name can't be blank")
    @NotNull(message = "name can't be null")
    private String name;
}
