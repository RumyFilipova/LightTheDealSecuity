package bg.softuni.lightthedeal.web.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/*@PasswordMatch(
        password = "newPassword",
        confirmPassword = "confirmPassword",
        message = "Passwords do not match"
)*/
public class ChangePasswordRequest{
        @NotBlank(message = "Current password is required")
        private String currentPassword;

        @NotBlank(message = "New password is required")
        @Size(min = 6, message = "New password must be at least 6 characters")
        private String newPassword;

        @NotBlank(message = "Please confirm your new password")
        private String confirmPassword;
}
