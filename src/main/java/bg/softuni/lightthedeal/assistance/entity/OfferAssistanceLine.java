package bg.softuni.lightthedeal.assistance.entity;

import bg.softuni.lightthedeal.offer.entity.Offer;
import bg.softuni.lightthedeal.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "offer_assistance_line")
public class OfferAssistanceLine {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Double quantity; // e.g. 3.0 hours of installation

    @Column(name = "price_at_time_of_offer", nullable = false)
    private BigDecimal priceAtTimeOfOffer;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "assistance_id", nullable = false)
    private Assistance assistance;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public BigDecimal subtotal() {
        return priceAtTimeOfOffer.multiply(BigDecimal.valueOf(quantity));
    }
}
