package bg.softuni.lightthedeal.web.DTO;
import bg.softuni.lightthedeal.assistance.entity.Category;
import bg.softuni.lightthedeal.assistance.entity.Unit;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter

public class AssistanceServiceResponse {

    private UUID id;
    private String name;
    private BigDecimal pricePerUnit;
    private String activityDescription;
    private Category category;
    private Unit unit;
    private Integer durationMinutes;
}
