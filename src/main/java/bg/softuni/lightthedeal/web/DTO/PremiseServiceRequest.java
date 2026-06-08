package bg.softuni.lightthedeal.web.DTO;

import bg.softuni.lightthedeal.customer.entity.Customer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class PremiseServiceRequest {

        @NotBlank(message = "* requered")
        private String name;
        private String address;
        private String description;

        @NotNull(message = "Please select a customer")
        private UUID customerId;
}
