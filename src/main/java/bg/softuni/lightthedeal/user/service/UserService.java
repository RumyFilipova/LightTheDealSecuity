package bg.softuni.lightthedeal.user.service;

import bg.softuni.lightthedeal.assistance.entity.Assistance;
import bg.softuni.lightthedeal.assistance.repository.AssistanceRepository;
import bg.softuni.lightthedeal.assistance.service.AssistanceService;
import bg.softuni.lightthedeal.customer.entity.Customer;
import bg.softuni.lightthedeal.customer.repository.CustomerRepository;
import bg.softuni.lightthedeal.customer.service.CustomerService;
import bg.softuni.lightthedeal.materials.entities.Material;
import bg.softuni.lightthedeal.materials.repository.MaterialRepository;
import bg.softuni.lightthedeal.materials.service.MaterialService;
import bg.softuni.lightthedeal.offer.entity.Offer;
import bg.softuni.lightthedeal.offer.repository.OfferRepository;
import bg.softuni.lightthedeal.offer.service.OfferService;
import bg.softuni.lightthedeal.order.entity.Order;
import bg.softuni.lightthedeal.order.repository.OrderRepository;
import bg.softuni.lightthedeal.order.service.OrderService;
import bg.softuni.lightthedeal.user.entity.Role;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.user.repository.UserRepository;
import bg.softuni.lightthedeal.web.DTO.AssistanceServiceRequest;
import bg.softuni.lightthedeal.web.DTO.RegisterRequestUser;
import bg.softuni.lightthedeal.web.DTO.UserUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // DOUBTS
    private final AssistanceRepository assistanceRepository;
    private final MaterialRepository materialRepository;
   private final CustomerRepository customerRepository;
   private final OfferRepository offerRepository;
    private final OrderRepository orderRepository;

    @Autowired
    //IoC through constructor and dependency injection
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AssistanceService assistanceService, MaterialService materialService, AssistanceRepository assistanceRepository, MaterialRepository mater, MaterialRepository materialRepository, CustomerRepository customerRepository, OfferRepository offerRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.assistanceRepository = assistanceRepository;
        this.materialRepository = materialRepository;
        this.customerRepository = customerRepository;
        this.offerRepository = offerRepository;
        this.orderRepository = orderRepository;

    }

    public void register (RegisterRequestUser registerRequestUser) {

        Optional<User> optionalUser = userRepository.findByUserName(registerRequestUser.username());

        if (optionalUser.isPresent()) {
            throw new RuntimeException("User with [%s] username already exists".formatted(registerRequestUser.username()));
        }

        User user = User.builder()
                .userName(registerRequestUser.username())
                .password(passwordEncoder.encode(registerRequestUser.password()))
                .email(registerRequestUser.email())
                .phoneNumber(registerRequestUser.phoneNumber())
                .role(Role.USER)
                .build();

        user = userRepository.save(user);

    }

    private Optional<User> getByUsername(User user) {

        return userRepository.findByUserName(user.getUserName());
    }

    public List<Material> getAllMaterialForUser(User user) {
        return materialRepository.findAllByUser(user);
    }
    public List<Assistance> getAllAssistanceForUser(User user){
        return assistanceRepository.findAllByUser(user);
    }
    public List<Customer> getAllCustomerForUser(User user) {
        return customerRepository.findAllByUser(user);
    }
    public List<Offer> getAllOffersForUSer(User user){
        return offerRepository.findAllByUser(user);
    }
    public List<Order> getAllOrdersForUSer(User user){
        return orderRepository.findAllByUser(user);
    }

    // UPDATE OFFER
    public User updateUser(UserUpdateRequest request,User user){

        user.setUserName(request.userName());
        user.setEmail(request.email());
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setPhoneNumber(request.phoneNumber());
        user.setProfilePicture(request.profilePicture());

        return userRepository.save(user);

    }





}
