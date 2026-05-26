package bg.softuni.lightthedeal.web.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.UniqueElements;

public record CustomerUpdateRequest(
        @NotBlank(message = "* required")
        String firstName,

        @NotBlank(message = "* required")
        String lastName,

        @NotBlank(message = "* required")
        @UniqueElements(message = "Customer with this number already exist")
        String phoneNumber,

        @NotBlank(message = "* required")
        @Email(message = "Please enter a valid email")
        @UniqueElements(message = "Customer with this e-mail already exist")
        String email,

        String address
) {
}
