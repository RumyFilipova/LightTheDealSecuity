package bg.softuni.lightthedeal.assistance.repository;

import bg.softuni.lightthedeal.assistance.entity.OfferAssistance;
import bg.softuni.lightthedeal.offer.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
public interface OfferAssistanceRepository extends JpaRepository<OfferAssistance,UUID> {
    List<OfferAssistance> findAllByOffer(Offer offer);
}
