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

    public List<Customer> getAllCustomersForUSer(User user) {

        List<Customer> customers = customerRepository.findAllByUsers(user);
        if(customers.isEmpty()){
            throw  new RuntimeException("The user do not have customers");
        }

        return customers;
    }

    public void createCustomer(CustomerServiceRequest customerServiceRequest, User user) {

        if (customerRepository.findByPhoneNumber(customerServiceRequest.getPhoneNumber()).isEmpty()) {
            throw new RuntimeException("Customer with this phone already exists");
        }
        if (customerRepository.findByEmail(customerServiceRequest.getEmail()).isEmpty()) {
            throw new RuntimeException("Customer with this email already exists");
        }

        Optional<Customer> optionalCustomer = customerRepository.findByEmail
                (customerServiceRequest.getEmail());


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
                    .firstName(customerServiceRequest.getFirstName())
                    .lastName(customerServiceRequest.getLastName())
                    .phoneNumber(customerServiceRequest.getPhoneNumber())
                    .email(customerServiceRequest.getEmail())
                    .address(customerServiceRequest.getAddress())
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

        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setEmail(request.getEmail());
        customer.setAddress(request.getAddress());

        return customerRepository.save(customer);
}

public void deleteCustomerForUser(UUID id, User user){
    Customer customer = getByIdAndUser(id,user);
    customerRepository.delete(customer);

}

}
