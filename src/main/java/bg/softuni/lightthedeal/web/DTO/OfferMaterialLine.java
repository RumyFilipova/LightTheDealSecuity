package bg.softuni.lightthedeal.web.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfferMaterialLine{
        private UUID materialId;
        private Double quantity;
}
