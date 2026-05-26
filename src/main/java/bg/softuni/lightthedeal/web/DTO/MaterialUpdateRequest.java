package bg.softuni.lightthedeal.web.DTO;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record MaterialUpdateRequest(

        @NotBlank(message = "* required")
        String name,

        @NotBlank(message = "* required")
        String type,

        String description,

        @NotBlank(message = "* required")
        String brand,

        @NotBlank(message = "* required")
        BigDecimal singlePrice
) {
}
