package bg.softuni.lightthedeal.customer.repository;

import bg.softuni.lightthedeal.customer.entity.Customer;
import bg.softuni.lightthedeal.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Optional<Customer> findByEmail(@NotBlank @UniqueElements(message = "Customer with this e-mail already exist") String email);

    // All customers linked to a given user
    List<Customer> findAllByUsers(User user);

    // Customer that is in belonging of the user
    Optional<Customer> findByIdAndUser(UUID id, User user);

    List<Customer> findAllByUser(User user);

    Optional<Customer> findByIdAndUsers(UUID id, User user);
}
