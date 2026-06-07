package bg.softuni.lightthedeal.web.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AssistanceUpdateRequest{

        @NotBlank(message = "* required")
        private String name;

        private String activityDescription;

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.01", message = "Price must be positive")
        private BigDecimal pricePerUnit;
}
