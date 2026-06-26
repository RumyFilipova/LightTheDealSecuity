package bg.softuni.lightthedeal.web.DTO;
import bg.softuni.lightthedeal.assistance.entity.OfferAssistanceLine;
import bg.softuni.lightthedeal.materials.entities.OfferMaterialLine;
import bg.softuni.lightthedeal.order.entity.StatusOrder;
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

public class OrderUpdateRequest {

    private UUID id;
    private String orderNumber;
    private StatusOrder status;
    private LocalDate deadline;
    private String customerName;
    private String premiseName;
    private BigDecimal totalAmount;
    private String note;
    private List<OfferMaterialLine> materials;
    private List<OfferAssistanceLine> assistants;
}
