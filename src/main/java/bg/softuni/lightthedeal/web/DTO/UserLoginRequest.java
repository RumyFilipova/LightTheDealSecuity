package bg.softuni.lightthedeal.web.DTO;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserLoginRequest {
    @Size(min = 6, message = "Username must be at least 6 characters")
    private String username;

    @Size(min = 6, message = "password must be at least 6 characters")
    private String password;
}
