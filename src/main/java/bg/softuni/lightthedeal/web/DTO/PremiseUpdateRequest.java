package bg.softuni.lightthedeal.web.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PremiseUpdateRequest{


        @NotBlank(message = "* requered")
        private String name;
        private String address;
        private String description;
}
