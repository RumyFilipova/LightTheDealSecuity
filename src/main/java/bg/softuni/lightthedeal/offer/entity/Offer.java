package bg.softuni.lightthedeal.offer.entity;

import bg.softuni.lightthedeal.assistance.entity.OfferAssistance;
import bg.softuni.lightthedeal.customer.entity.Customer;
import bg.softuni.lightthedeal.order.entity.Order;
import bg.softuni.lightthedeal.premise.entity.Premise;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.materials.entities.OfferMaterial;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
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

    @Column(name = "valid_until", nullable = false)
    private LocalDate validUntil;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "status_offer")
    @Enumerated(EnumType.STRING)
    private StatusOffer statusOffer;

    @Column
    private String notes;

    //OneOfferOneUser
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //OneOfferOneCustomer
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer; // who ordered the work

    @ManyToOne
    @JoinColumn(name = "premise_id")
    private Premise premise;// where the work is done

    @OneToOne(mappedBy = "offer")
    private Order order;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OfferMaterial> materials = new ArrayList<>();

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List <OfferAssistance> assistanceItems = new ArrayList<>();


}
