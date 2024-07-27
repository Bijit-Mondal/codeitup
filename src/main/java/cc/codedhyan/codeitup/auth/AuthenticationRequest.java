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
public class AuthenticationRequest {
    @NotBlank(message = "Email can't be blank")
    @NotNull(message = "Email can't be null")
    @Email
    private String email;

    @NotBlank(message = "password can't be blank")
    @NotNull(message = "password can't be null")
    String password;
}
