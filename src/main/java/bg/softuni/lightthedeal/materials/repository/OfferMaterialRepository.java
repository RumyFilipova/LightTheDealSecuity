package bg.softuni.lightthedeal.materials.repository;

import bg.softuni.lightthedeal.materials.entities.OfferMaterial;
import bg.softuni.lightthedeal.offer.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OfferMaterialRepository extends JpaRepository<OfferMaterial, UUID> {

    List<OfferMaterial> findAllByOffer(Offer offer);
}
