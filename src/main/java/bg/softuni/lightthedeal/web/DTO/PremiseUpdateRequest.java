package bg.softuni.lightthedeal.web.DTO;

import jakarta.validation.constraints.NotBlank;

public record PremiseUpdateRequest(

        @NotBlank(message = "* requered")
        String name,
        String address,
        String description
) {
}
