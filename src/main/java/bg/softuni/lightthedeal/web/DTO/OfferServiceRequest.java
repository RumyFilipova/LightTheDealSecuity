package bg.softuni.lightthedeal.web.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class OfferServiceRequest {

    @NotBlank(message = "* required")
    private String offerNumber;

    @NotNull
    private UUID customerId;

    @NotNull
    private UUID premiseId;

    private LocalDate validUntil;

    private LocalDate deadline;

    private String note;

    @Valid
    private List<OfferMaterialLine> materials;
    @Valid
    private List<OfferAssistanceLine> assistants;
}
