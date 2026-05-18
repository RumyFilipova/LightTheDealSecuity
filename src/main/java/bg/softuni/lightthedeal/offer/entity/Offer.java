package bg.softuni.lightthedeal.offer.entity;

import bg.softuni.lightthedeal.assistance.entity.OfferAssistance;
import bg.softuni.lightthedeal.order.entity.Order;
import bg.softuni.lightthedeal.premise.entity.Premise;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.materials.entities.OfferMaterial;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "offer_number")
    private String offerNumber;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column
    private LocalDate deadline;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "status_offer")
    @Enumerated(EnumType.STRING)
    private StatusOffer statusOffer;


    //OneOfferOneUser
    @ManyToOne
    private User user;

    //OneOfferOneCustomer
    @ManyToOne
    private Premise premise;

    @OneToOne(mappedBy = "offer")
    private Order order;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OfferMaterial> materials = new ArrayList<>();

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List <OfferAssistance> assistanceItems = new ArrayList<>();


}
