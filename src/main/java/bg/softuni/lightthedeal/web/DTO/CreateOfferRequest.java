package bg.softuni.lightthedeal.web.DTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record CreateOfferRequest(
        UUID premiseId,
        UUID customerId,
        LocalDate deadline,
        List<OfferMaterialLine> materials,
        List<OfferAssistanceLine> assistances
) {
}


