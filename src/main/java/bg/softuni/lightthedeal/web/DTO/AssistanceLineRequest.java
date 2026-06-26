package bg.softuni.lightthedeal.web.DTO;
import lombok.*;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AssistanceLineRequest{
        private UUID assistanceId;
        private Double quantity;
        private String description;
}
