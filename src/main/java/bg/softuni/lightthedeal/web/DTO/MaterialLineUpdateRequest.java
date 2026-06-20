package bg.softuni.lightthedeal.web.DTO;
import bg.softuni.lightthedeal.materials.entities.Category;
import bg.softuni.lightthedeal.materials.entities.Unit;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MaterialLineUpdateRequest {

    @NotNull
    private UUID lineId;
    @NotNull
    private UUID materialId;
    @NotBlank(message = "* required")
    private String name;

    @NotBlank(message = "* required")
    private String type;
    @NotBlank(message = "* required")
    private String description;
    @NotBlank(message = "* required")
    private String brand;

    @NotNull(message = "* required")
    @Positive(message = "Quantity must be greater than 0")
    private Double quantity;

    @NotNull(message = "* required")
    private BigDecimal singlePrice;

    @NotBlank
    private Unit unit;
    @NotBlank
    private Category category;
}
