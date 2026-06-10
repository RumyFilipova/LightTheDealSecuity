package bg.softuni.lightthedeal.premise.entity;

import bg.softuni.lightthedeal.customer.entity.Customer;
import bg.softuni.lightthedeal.offer.entity.Offer;
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
@Table(name = "premises")
public class Premise {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @Column(nullable = false)
    private String name;           // e.g. "Main apartment", "Office floor 2"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PremiseType type;

    @Column
    private String address;

    @Column
    private String description;    // type: apartment / office / new build etc.

    @Column
    private Double area;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "premise", fetch = FetchType.LAZY)
    private List<Offer> offers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

}
