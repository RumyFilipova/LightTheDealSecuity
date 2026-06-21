package bg.softuni.lightthedeal.assistance.repository;
import bg.softuni.lightthedeal.assistance.entity.Assistance;

import bg.softuni.lightthedeal.offer.entity.Offer;
import bg.softuni.lightthedeal.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssistanceRepository extends JpaRepository<Assistance, UUID> {


    List<Assistance> findAllByUser(User user);

    Optional<Assistance> findByIdAndUser(UUID id, User user);


}
