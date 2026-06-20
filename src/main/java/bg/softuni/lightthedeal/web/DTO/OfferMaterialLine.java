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
@NoArgsConstructor
@AllArgsConstructor

public class OfferMaterialLine{

        @NotNull
        private UUID materialId;

        @NotNull(message = "* required")
        @Positive(message = "Quantity must be greater than 0")
        private Double quantity;



}
