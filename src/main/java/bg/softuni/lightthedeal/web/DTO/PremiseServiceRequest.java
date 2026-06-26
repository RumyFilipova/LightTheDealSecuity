package bg.softuni.lightthedeal.web.DTO;
import bg.softuni.lightthedeal.premise.entity.PremiseType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class PremiseServiceRequest {

        @NotBlank(message = "* required")
        private String name;
        private PremiseType type;
        private String address;
        private String description;
        private Double area;

        @NotNull(message = "Please select a customer")
        private UUID customerId;
}
