package bg.softuni.lightthedeal.materials.entities;

import bg.softuni.lightthedeal.offer.entity.Offer;
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
@Table(name = "offer_materials")
public class OfferMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Double quantity; // e.g. 50.0 meters of cable

    @Column(name = "price_at_time_of_offer", nullable = false)
    private BigDecimal priceAtTimeOfOffer;

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;


    public BigDecimal subtotal() {
        return priceAtTimeOfOffer.multiply(BigDecimal.valueOf(quantity));
    }
}
