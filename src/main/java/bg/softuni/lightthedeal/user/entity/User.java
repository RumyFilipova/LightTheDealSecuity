package bg.softuni.lightthedeal.user.entity;
import bg.softuni.lightthedeal.customer.entity.Customer;
import bg.softuni.lightthedeal.offer.entity.Offer;
import bg.softuni.lightthedeal.order.entity.Order;
import bg.softuni.lightthedeal.premise.entity.Premise;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column (nullable = false)
    private String password;

    @Column (nullable = false,unique = true)
    private String email;

    @Column (name = "first_name",nullable = false)
    private String firstName;

    @Column (name = "last_name",nullable = false)
    private String lastName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_customer",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns =@JoinColumn(name = "customer_id")
    )
    private List<Customer> customers;

    @ManyToOne
    @JoinColumn(name = "premise_id")
    private Premise premise;


    //OneUserManyOffers
    @OneToMany
    private List<Offer> offers;

    //OneUserManyOrders
    @OneToMany
    private List<Order> orders;


}
