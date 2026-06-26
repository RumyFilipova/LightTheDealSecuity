package bg.softuni.lightthedeal.web.DTO;
import bg.softuni.lightthedeal.customer.entity.CustomerType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CustomerServiceRequest {

        @NotBlank(message = "* required")
        private String firstName;

        @NotBlank(message = "* required")
        private String lastName;

        @NotBlank(message = "* required")
        @UniqueElements(message = "Customer with this number already exist")
        private String phoneNumber;

        @NotBlank(message = "* required")
        @Email(message = "Please enter a valid email")
        @UniqueElements(message = "Customer with this e-mail already exist")
        private String email;

        @NotBlank
        private String address;

        private String companyName;

        private String customerDetails;
        private CustomerType customerType;
}
