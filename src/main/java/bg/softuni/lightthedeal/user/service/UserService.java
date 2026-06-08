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
import bg.softuni.lightthedeal.premise.entity.Premise;
import bg.softuni.lightthedeal.user.entity.Role;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.user.repository.UserRepository;
import bg.softuni.lightthedeal.web.DTO.*;
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

    public void register(RegisterRequestUser registerRequestUser) {

        Optional<User> optionalUser = userRepository.findByUsername(registerRequestUser.getUsername());

        if (optionalUser.isPresent()) {
            throw new RuntimeException("User with [%s] username already exists".formatted(registerRequestUser.getUsername()));
        }

        User user = User.builder()
                .username(registerRequestUser.getUsername())
                .pasasword(passwordEncoder.encode(registerRequestUser.getPassword()))
                .email(registerRequestUser.getEmail())
                .phoneNumber(registerRequestUser.getPhoneNumber())
                .role(Role.USER)
                .build();

        user = userRepository.save(user);

    }

    public User login(UserLoginRequest logReg){

        Optional<User> optUser = userRepository.findByUsername(logReg.getUsername());

        if(optUser.isEmpty()){
            throw new RuntimeException("The user does not exist");
        }

        String rawPass =  logReg.getPassword();
        String hashedPass = optUser.get().getPasasword();

        if(!passwordEncoder.matches(rawPass,hashedPass)){
            throw  new RuntimeException("Incorrect username or password");
        }


        return optUser.get();
    }



    public List<Material> getAllMaterialForUser(User user) {
        return materialRepository.findAllByUser(user);
    }

    public List<Assistance> getAllAssistanceForUser(User user) {
        return assistanceRepository.findAllByUser(user);
    }

    public List<Customer> getAllCustomerForUser(User user) {
        return customerRepository.findAllByUsers(user);
    }

    public List<Offer> getAllOffersForUSer(User user) {
        return offerRepository.findByUser(user);
    }

    public List<Order> getAllOrdersForUSer(User user) {
        return orderRepository.findAllByUser(user);
    }

    // UPDATE USER
    public User updateUser(UserUpdateRequest request, UUID id) {

        User user = userRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(request.userName());
        user.setEmail(request.email());
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setPhoneNumber(request.phoneNumber());
        user.setProfilePicture(request.profilePicture());

        return userRepository.save(user);

    }

    public void deleteUser(UUID id, User user) {

        User user1 = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user1);
    }


    public List<User> getAll() {

        return userRepository.findAll();
    }

    public User getByUsername(String username) {

        return userRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("User with %s username does not exist".formatted(username)));
    }

    public User getById(UUID id) {

        return userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User with id %s does not exist".formatted(id)));
    }

    public void removeProfilePicture(UUID id) {

        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User with id %s does not exist".formatted(id)));

        user.setProfilePicture(null);
        userRepository.save(user);
    }

    public List<Premise> getAllPremisesForTheUser(User user) {

        List<Premise> premises;
        premises = userRepository.getAllPremises(user.getCustomers().stream().toList());

        return premises;
    }
}
