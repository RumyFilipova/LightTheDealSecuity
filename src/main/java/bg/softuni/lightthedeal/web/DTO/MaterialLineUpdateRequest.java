package bg.softuni.lightthedeal.web.DTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MaterialLineUpdateRequest {

    @NotNull
    private UUID lineId;
    @NotNull
    private UUID materialId;

    @NotNull(message = "* required")
    @Positive(message = "Quantity must be greater than 0")
    private Double quantity;
}
