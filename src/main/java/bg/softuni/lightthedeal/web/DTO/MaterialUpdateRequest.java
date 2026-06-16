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
@NoArgsConstructor
@AllArgsConstructor
public class MaterialUpdateRequest {

        @NotBlank(message = "* required")
        private String name;

        @NotBlank(message = "* required")
        private String type;

        private String description;

        @NotBlank(message = "* required")
        private String brand;

        @NotNull(message = "* required")
        private BigDecimal singlePrice;
        @NotNull(message = "* required")
        private Double quantity;
        private Unit unit;
        private Category category;

}
