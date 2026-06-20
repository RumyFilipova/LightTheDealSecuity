package bg.softuni.lightthedeal.web.DTO;


import bg.softuni.lightthedeal.offer.entity.StatusOffer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class OfferUpdateRequest {


    private UUID offerID;
    private LocalDate validUntil;
    private String note;
    private StatusOffer statusOffer;
    @Valid
    private List<MaterialLineRequest> materials;
    @Valid
    private List<AssistanceLineRequest> assistants;
}
