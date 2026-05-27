package bg.softuni.lightthedeal.assistance.repository;

import bg.softuni.lightthedeal.assistance.entity.OfferAssistance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OfferAssistanceRepository extends JpaRepository<OfferAssistance,UUID> {
}
