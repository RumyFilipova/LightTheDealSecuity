package bg.softuni.lightthedeal.web.DTO;

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

        String description;

        @NotBlank(message = "* required")
        private String brand;

        @NotNull(message = "* required")
        private BigDecimal singlePrice;

}
