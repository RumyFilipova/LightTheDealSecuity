package bg.softuni.lightthedeal.web.DTO;
import bg.softuni.lightthedeal.customer.entity.CustomerType;
import lombok.*;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CustomerServiceResponse {

    private UUID id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String address;
    private String companyName;
    private String customerDetails;
    private CustomerType customerType;
}
