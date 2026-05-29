package bg.softuni.lightthedeal.web.DTO;

import bg.softuni.lightthedeal.offer.entity.Offer;
import java.time.LocalDate;
import java.util.UUID;

public record CreateOrderRequest(

        UUID offerId,

        LocalDate deadline

) {
}
