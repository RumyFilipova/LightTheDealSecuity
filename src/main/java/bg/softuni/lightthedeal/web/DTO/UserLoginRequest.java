package bg.softuni.lightthedeal.web.DTO;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public record UserLoginRequest(
    @Size(min = 6, message = "Username must be at least 6 characters")
    String username,

    @Size(min = 6, message = "password must be at least 6 characters")
    String password

) {
}
