package bg.softuni.lightthedeal.web.controllers;

import bg.softuni.lightthedeal.assistance.entity.Category;
import bg.softuni.lightthedeal.assistance.entity.Unit;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class OfferAssistanceController {

    @NotBlank(message = "* required")
    private String name;

    private BigDecimal pricePerUnit;
    private Category category;
    private Unit unit;
    private String description;
}
