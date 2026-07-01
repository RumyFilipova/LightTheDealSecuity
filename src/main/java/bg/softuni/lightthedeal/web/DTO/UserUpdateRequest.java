package bg.softuni.lightthedeal.web.DTO;
import bg.softuni.lightthedeal.user.entity.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserUpdateRequest{

        @NotBlank(message = "* required")
        private String userName;
        @NotBlank(message = "* required")
        private String email;
        @NotBlank(message = "* required")
        private String firstName;
        @NotBlank(message = "* required")
        private String lastName;
        @NotBlank(message = "* required")
        private String phoneNumber;

        private String profilePicture;
        private Role role;
}
