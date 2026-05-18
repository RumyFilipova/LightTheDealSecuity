package bg.softuni.lightthedeal.materials.entities;

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
@Table(name = "offer_materials")
public class OfferMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;

    @Column(nullable = false)
    private Double quantity; // e.g. 50.0 meters of cable

    // derived helper — don't store this, calculate on the fly
    public Double subtotal() {
        return quantity * material.getSinglePrice();
    }
}
