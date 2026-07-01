package bg.softuni.lightthedeal.web.DTO;

import bg.softuni.lightthedeal.premise.entity.Premise;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TaskCreateRequest {
    private UUID id;
    private String staffFirstName;
    private String staffLastName;
    private String staffRole;
    private Premise staffPremise;
}
