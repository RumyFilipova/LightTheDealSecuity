package bg.softuni.lightthedeal.web.DTO;

import bg.softuni.lightthedeal.offer.entity.Offer;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CreateOrderRequest{
        private UUID offerId;
        private LocalDate deadline;
}
