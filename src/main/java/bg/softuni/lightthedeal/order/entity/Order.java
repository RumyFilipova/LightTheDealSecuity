package bg.softuni.lightthedeal.order.entity;

import bg.softuni.lightthedeal.customer.entity.Customer;
import bg.softuni.lightthedeal.offer.entity.Offer;
import bg.softuni.lightthedeal.premise.entity.Premise;
import bg.softuni.lightthedeal.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

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
    private statusOrder stausOrder;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "completed_on")
    private LocalDate completedOn;

    //ManyOrderOneUser
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //ManyOrderOnePremise
    @ManyToOne
    @JoinColumn(name = "premise_id", nullable = false)
    private Premise premise;

    @OneToOne
    @JoinColumn(name = "offer_id", nullable = false, unique = true)
    private Offer offer;
}
