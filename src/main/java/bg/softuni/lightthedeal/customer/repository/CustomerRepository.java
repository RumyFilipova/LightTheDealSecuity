package bg.softuni.lightthedeal.customer.repository;

import bg.softuni.lightthedeal.customer.entity.Customer;
import bg.softuni.lightthedeal.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Optional<Customer> findByEmail(@NotBlank(message = "* required") @UniqueElements(message = "Customer with this email already exist") String email);

    // Customer that is in belonging of the user
    Optional<Customer> findByIdAndUsers(UUID id, User user);

    List <Customer> findAllByUsers(User user);

    Optional <Customer> findByPhoneNumber(@NotBlank(message = "* required") @UniqueElements(message = "Customer with this number already exist")
                                          String phoneNumber);
}
