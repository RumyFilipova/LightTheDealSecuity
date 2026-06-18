package bg.softuni.lightthedeal.web.DTO;

import bg.softuni.lightthedeal.assistance.entity.Category;
import bg.softuni.lightthedeal.assistance.entity.Unit;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AssistanceUpdateRequest{

        private UUID id;
        @NotBlank(message = "* required")
        private String name;

        private String activityDescription;
        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.01", message = "Price must be positive")
        private BigDecimal pricePerUnit;
        private Category category;
        private Unit unit;

}
