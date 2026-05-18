package bg.softuni.lightthedeal.web.DTO;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public record RegisterRequestUser(

      // i chose record ,because it is immutable and i can save the data for my user more securely that it can not be modified
    @NotBlank
    @Size(min = 6, message = "Username must be more than 6 symbols")
    String username,

    @NotBlank
    @Size(min = 8)
    String password,

    @NotBlank
    @Email
    String email,

    @NotBlank
    @Size(min = 10, max = 10, message = "The number must be exactly 10 characters")
    String phoneNumber
) {

}
