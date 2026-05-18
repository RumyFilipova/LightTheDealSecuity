package bg.softuni.lightthedeal.premise.entity;

import bg.softuni.lightthedeal.customer.entity.Customer;
import bg.softuni.lightthedeal.offer.entity.Offer;
import bg.softuni.lightthedeal.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    private String location;

    //OnePremiseManyUser
    @OneToMany(mappedBy = "premise")
    private List<User> users = new ArrayList<>();

    //OnePremiseManyOffers

    @OneToMany(mappedBy = "premise")
    private List<Offer> offers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

}
