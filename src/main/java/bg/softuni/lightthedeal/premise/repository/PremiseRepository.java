package bg.softuni.lightthedeal.premise.repository;
import bg.softuni.lightthedeal.customer.entity.Customer;
import bg.softuni.lightthedeal.premise.entity.Premise;
import bg.softuni.lightthedeal.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PremiseRepository extends JpaRepository<Premise, UUID> {

    List<Premise> findAllByCustomer(Customer customer);

    Optional<Premise> findByIdAndUser(UUID uuid, User user);
}
