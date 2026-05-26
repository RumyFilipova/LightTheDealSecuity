package bg.softuni.lightthedeal.web.DTO;

import jakarta.validation.constraints.NotBlank;

public record UserUpdateRequest(
@NotBlank(message = "* required")
        String userName,
@NotBlank(message = "* required")
        String email,
@NotBlank(message = "* required")
        String firstName,
@NotBlank(message = "* required")
        String lastName,
@NotBlank(message = "* required")
        String phoneNumber,
@NotBlank(message = "* required")
        String profilePicture
        ) {
}
