package bg.softuni.lightthedeal.web.DTO;

import lombok.*;

import java.util.UUID;
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class OfferOptionResponse {

    private UUID id;
    private String offerName;
    private String customerFirstName;
    private String customerLastName;
    private String premiseName;
    private String premiseAddress;
}
