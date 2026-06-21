package bg.softuni.lightthedeal.web.DTO;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OfferServiceResponse {

    @NotNull
    private UUID customerId;
    @NotNull
    private UUID premiseId;
    private String offerName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate validUntil;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate deadline;
    private String note;

    @Valid
    private List<MaterialLineRequest> materials;
    @Valid
    private List<AssistanceLineRequest> assistants;
}
