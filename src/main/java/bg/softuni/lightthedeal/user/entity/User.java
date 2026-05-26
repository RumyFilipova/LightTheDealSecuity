package bg.softuni.lightthedeal.user.entity;
import bg.softuni.lightthedeal.assistance.entity.Assistance;
import bg.softuni.lightthedeal.customer.entity.Customer;
import bg.softuni.lightthedeal.materials.entities.Material;
import bg.softuni.lightthedeal.offer.entity.Offer;
import bg.softuni.lightthedeal.order.entity.Order;
import bg.softuni.lightthedeal.premise.entity.Premise;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    // Each customer belongs to this user (technician)
    @ManyToMany
    private List <Customer> customers = new ArrayList<>();

    // User's personal catalogue of assistance services
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Assistance> assistanceList = new ArrayList<>();

    // User's personal catalogue of materials
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Material> materialList = new ArrayList<>();

    // Offers created by this user
    @OneToMany(mappedBy = "user")
    private List<Offer> offers = new ArrayList<>();

    // Orders created by this user
    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Premise> premises = new ArrayList<>();
}
