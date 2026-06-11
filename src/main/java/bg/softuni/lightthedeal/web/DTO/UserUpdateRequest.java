package bg.softuni.lightthedeal.web.DTO;

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
        @NotBlank(message = "* required")
        private String profilePicture;
}
