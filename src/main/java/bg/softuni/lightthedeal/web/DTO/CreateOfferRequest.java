package bg.softuni.lightthedeal.web.DTO;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CreateOfferRequest{
       private UUID premiseId;
       private UUID customerId;
       private LocalDate deadline;

       private List<OfferMaterialLine> materials;
       private List<OfferAssistanceLine> assistants;
}


