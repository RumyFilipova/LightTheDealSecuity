package bg.softuni.lightthedeal.web.DTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MaterialLineRequest{

        @NotNull
        private UUID materialId;

        @NotNull(message = "* required")
        @Positive(message = "Quantity must be greater than 0")
        private Double quantity;
        private String description;


}
