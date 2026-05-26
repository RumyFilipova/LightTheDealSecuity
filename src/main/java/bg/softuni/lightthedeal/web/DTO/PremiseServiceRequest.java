package bg.softuni.lightthedeal.web.DTO;

import bg.softuni.lightthedeal.customer.entity.Customer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PremiseServiceRequest(

        @NotBlank(message = "* requered")
        String name,
        String address,
        String description,

        @NotNull(message = "Please select a customer")
        UUID customerId
) {
}
