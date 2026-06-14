package bg.softuni.lightthedeal.customer.service;

import bg.softuni.lightthedeal.customer.entity.Customer;
import bg.softuni.lightthedeal.customer.repository.CustomerRepository;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.user.repository.UserRepository;
import bg.softuni.lightthedeal.web.DTO.CustomerServiceRequest;
import bg.softuni.lightthedeal.web.DTO.CustomerUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    public List<Customer> getAllCustomersForUser(User user) {
        List<Customer> customers = customerRepository.findAllByUsers(user);

        return customers;
    }

    public void createCustomer(CustomerServiceRequest customerServiceRequest, User user) {

        if(customerRepository.findByPhoneNumber(customerServiceRequest.getPhoneNumber()).isPresent()){
            throw new RuntimeException("Customer with this phone already exists");
        }

        if(customerRepository.findByEmail(customerServiceRequest.getEmail()).isPresent()){
            throw new RuntimeException("Customer with this e-mail already exists");
        }

        Customer customer = Customer.builder()
                .users(new ArrayList<>(List.of(user)))
                .firstName(customerServiceRequest.getFirstName())
                .lastName(customerServiceRequest.getLastName())
                .phoneNumber(customerServiceRequest.getPhoneNumber())
                .email(customerServiceRequest.getEmail())
                .address(customerServiceRequest.getAddress())
                .build();

        customerRepository.save(customer);
        user.getCustomers().add(customer);
        userRepository.save(user);
    }

    public Customer getByIdAndUser(UUID id, User user) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer $s does not exist".formatted(user.getId())));

        return customer;
    }

    public Customer updateCustomer(CustomerUpdateRequest customerUpdateRequest, UUID id, User user) {

        Customer customer = getByIdAndUser(id, user);

        customer.setFirstName(customerUpdateRequest.getFirstName());
        customer.setLastName(customerUpdateRequest.getLastName());
        customer.setPhoneNumber(customerUpdateRequest.getPhoneNumber());
        customer.setEmail(customerUpdateRequest.getEmail());
        customer.setAddress(customerUpdateRequest.getAddress());

        return customerRepository.save(customer);
    }

    public void deleteById(UUID id, User user) {
        Customer customer = getByIdAndUser(id, user);
        customerRepository.delete(customer);
    }


}
