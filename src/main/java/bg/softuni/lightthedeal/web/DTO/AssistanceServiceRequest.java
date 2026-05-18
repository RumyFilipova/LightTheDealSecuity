package bg.softuni.lightthedeal.web.DTO;

import jakarta.validation.constraints.NotBlank;

public record AssistanceServiceRequest(

        @NotBlank
        String name,

        String activityDescription,

        @NotBlank
        Double pricePerUnit

) {
}
