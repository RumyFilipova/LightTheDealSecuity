package bg.softuni.lightthedeal.web.DTO;
import bg.softuni.lightthedeal.offer.entity.StatusOffer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class OfferServiceRequest {

    @NotNull
    private UUID customerId;

    @NotNull
    private UUID premiseId;
    private String offerName;
    private LocalDateTime createdOn;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate validUntil;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate deadline;

    private String note;
    private StatusOffer statusOffer;

    @Valid
    private List<MaterialLineRequest> materials;
    @Valid
    private List<AssistanceLineRequest> assistants;
}
