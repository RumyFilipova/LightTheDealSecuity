package bg.softuni.lightthedeal.web.DTO;

import bg.softuni.lightthedeal.materials.entities.Category;
import bg.softuni.lightthedeal.materials.entities.Unit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class MaterialServiceResponse {

    private UUID id;
    private String name;
    private String type;
    private String brand;
    private String description;
    private BigDecimal singlePrice;
    private Double quantity;
    private Unit unit;
    private Category category;
}
