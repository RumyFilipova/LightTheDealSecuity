package bg.softuni.lightthedeal.web.DTO;
import bg.softuni.lightthedeal.materials.entities.Category;
import bg.softuni.lightthedeal.materials.entities.Unit;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MaterialServiceRequest {

        @NotBlank(message = "* required")
        private String name;

        @NotBlank(message = "* required")
        private String type;

        @NotNull(message = "* required")
        private Double quantity;

        private String description;

        @NotBlank(message = "* required")
        private String brand;

        private Category category;
        private Unit unit;

        @NotNull(message = "* required")
        private BigDecimal singlePrice;


}

