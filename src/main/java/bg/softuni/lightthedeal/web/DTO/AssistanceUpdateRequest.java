package bg.softuni.lightthedeal.web.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record AssistanceUpdateRequest(
        @NotBlank(message = "* required")
        String name,

        String activityDescription,

        @NotBlank(message = "Price is required")
        @DecimalMin(value = "0.01", message = "Price must be positive")
        BigDecimal pricePerUnit
) {
}
