package bg.softuni.lightthedeal.web.DTO;

import bg.softuni.lightthedeal.customer.entity.Customer;
import bg.softuni.lightthedeal.premise.entity.PremiseType;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PremiseUpdateRequest {


    private UUID id;
    @NotBlank(message = "* requered")
    private String name;
    private String address;
    private String description;
    private PremiseType type;
    private Double area;
    private Customer customer;
}
