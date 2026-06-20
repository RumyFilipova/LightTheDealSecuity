package bg.softuni.lightthedeal.web.DTO;

import bg.softuni.lightthedeal.order.entity.StatusOrder;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdateRequest {

    private UUID orderId;
    private String description;
    private StatusOrder stausOrder;
    private LocalDate deadline;
}
