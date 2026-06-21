package bg.softuni.lightthedeal.web.DTO;
import bg.softuni.lightthedeal.order.entity.StatusOrder;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class OrderCreateResponse {

    private UUID offerId;
    private String orderNumber;
    private String description;
    private LocalDate deadline;
    private StatusOrder stausOrder;
    private LocalDateTime createdOn;
    private LocalDate completedOn;

}
