package bg.softuni.lightthedeal.order.repository;
import bg.softuni.lightthedeal.customer.entity.Customer;
import bg.softuni.lightthedeal.order.entity.Order;
import bg.softuni.lightthedeal.premise.entity.Premise;
import bg.softuni.lightthedeal.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findAllByUser(User user);
    List<Order> findAllByCustomer(Customer customer);
    List<Order> findAllByPremise(Premise premise);
}
