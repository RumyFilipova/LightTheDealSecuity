package bg.softuni.lightthedeal.web.DTO;

import java.math.BigDecimal;
import java.util.UUID;

public record OfferAssistanceLine(
        UUID assistanceId,
        BigDecimal quantity
) {
}
