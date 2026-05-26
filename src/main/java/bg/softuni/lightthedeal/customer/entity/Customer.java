package bg.softuni.lightthedeal.customer.entity;

import bg.softuni.lightthedeal.premise.entity.Premise;
import bg.softuni.lightthedeal.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String address;

    //OneCustomerManyUsers
    @ManyToMany(mappedBy = "customers")
    private List<User> users = new ArrayList<>();

    // OneCustomerManuPremise

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "customer")
    private List<Premise> premises = new ArrayList<>();

}
