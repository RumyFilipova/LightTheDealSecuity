package bg.softuni.lightthedeal.web.DTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RegisterRequestUser {
    @NotBlank
    @Size(min = 6, message = "Username must be more than 6 symbols")
    private String username;

    @NotBlank
    @Size(min = 8)
    private String password;

    @NotBlank
    @Size(min = 8)
    private String confirmPassword;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 13, max = 13, message = "The number must be exactly 13 characters")
    private String phoneNumber;

}
