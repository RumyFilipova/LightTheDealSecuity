package bg.softuni.lightthedeal.web.DTO;
import jakarta.validation.constraints.NotBlank;
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

        private String description;

        @NotBlank(message = "* required")
        private String brand;

        @NotBlank(message = "* required")
        private BigDecimal singlePrice;
}
