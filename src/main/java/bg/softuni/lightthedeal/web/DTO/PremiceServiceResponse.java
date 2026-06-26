package bg.softuni.lightthedeal.web.DTO;
import bg.softuni.lightthedeal.premise.entity.PremiseType;
import lombok.Builder;
import lombok.Getter;
import java.util.UUID;

@Builder
@Getter

public class PremiceServiceResponse {
    private UUID id;
    private String name;
    private PremiseType type;
    private String address;
    private UUID customerId;
    private String customerName;
    private Double area;
    private String description;

}
