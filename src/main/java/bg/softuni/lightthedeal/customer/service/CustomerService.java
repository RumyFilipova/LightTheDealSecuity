package bg.softuni.lightthedeal.customer.service;

import bg.softuni.lightthedeal.customer.entity.Customer;
import bg.softuni.lightthedeal.customer.repository.CustomerRepository;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.web.DTO.CustomerServiceRequest;
import bg.softuni.lightthedeal.web.DTO.CustomerUpdateRequest;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void getAllCustomersForUSer(User user) {

    }

    public void createCustomer(CustomerServiceRequest customerServiceRequest, User user) {


        Optional<Customer> optionalCustomer = customerRepository.findByEmail
                (customerServiceRequest.email());

        if (optionalCustomer.isPresent()) {

            // customer exists - just link this user to them

            Customer customer = optionalCustomer.get();
            if (!customer.getUsers().contains(user)) {
                customer.getUsers().add(user);
                customerRepository.save(customer);
            }

        } else {

            // create the customer and link it with the user

            Customer customer = Customer.builder()
                    .users(new ArrayList<>(List.of(user)))
                    .firstName(customerServiceRequest.firstName())
                    .lastName(customerServiceRequest.lastName())
                    .phoneNumber(customerServiceRequest.phoneNumber())
                    .email(customerServiceRequest.email())
                    .address(customerServiceRequest.address())
                    .build();

            customerRepository.save(customer);
        }

    }

    public Customer getByIdAndUser(UUID id, User user) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Customer %s not found".formatted(id)));
        return customer;
    }

public Customer updateCustomer (CustomerUpdateRequest request, UUID id, User user) {

        Customer customer = getByIdAndUser(id,user);

        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setPhoneNumber(request.phoneNumber());
        customer.setEmail(request.email());
        customer.setAddress(request.address());

        return customerRepository.save(customer);
}

public void deleteCustomerForUser(UUID id, User user){
    Customer customer = getByIdAndUser(id,user);
    customerRepository.delete(customer);

}

}
