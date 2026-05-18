package bg.softuni.lightthedeal.assistance.entity;

import bg.softuni.lightthedeal.offer.entity.Offer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "offer_assistance")
public class OfferAssistance {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "assistance_id", nullable = false)
    private Assistance assistance;

    @Column(nullable = false)
    private Double quantity; // e.g. 3.0 hours of installation

    public Double subtotal() {
        return quantity * assistance.getPricePerUnit();
    }
}
