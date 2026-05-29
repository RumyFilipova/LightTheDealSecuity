package bg.softuni.lightthedeal.order.entity;

import bg.softuni.lightthedeal.customer.entity.Customer;
import bg.softuni.lightthedeal.offer.entity.Offer;
import bg.softuni.lightthedeal.premise.entity.Premise;
import bg.softuni.lightthedeal.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "order_number")
    private String orderNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_order")
    private StatusOrder stausOrder;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "completed_on")
    private LocalDate completedOn;

    //ManyOrderOneUser
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //ManyOrderOnePremise
    @ManyToOne
    @JoinColumn(name = "premise_id", nullable = false)
    private Premise premise;


    // always linked to the offer that created it
    @OneToOne
    @JoinColumn(name = "offer_id", nullable = false, unique = true)
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}
