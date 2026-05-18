package bg.softuni.lightthedeal.user.service;

import bg.softuni.lightthedeal.assistance.repository.AssistanceRepository;
import bg.softuni.lightthedeal.assistance.service.AssistanceService;
import bg.softuni.lightthedeal.customer.repository.CustomerRepository;
import bg.softuni.lightthedeal.materials.repository.MaterialRepository;
import bg.softuni.lightthedeal.materials.service.MaterialService;
import bg.softuni.lightthedeal.offer.entity.Offer;
import bg.softuni.lightthedeal.offer.repository.OfferRepository;
import bg.softuni.lightthedeal.order.repository.OrderRepository;
import bg.softuni.lightthedeal.user.entity.Role;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.user.repository.UserRepository;
import bg.softuni.lightthedeal.web.DTO.RegisterRequestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // DOUBTS
    private final AssistanceService assistanceService;
    private final MaterialService materialService;
    // TO BE CONFIRMED
   // private final CustomerRepository customerRepository;
   // private final OfferRepository offerRepository;
   // private final OrderRepository orderRepository;

    @Autowired
    //IoC through constructor and dependency injection
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AssistanceService assistanceService, MaterialService materialService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

        this.assistanceService = assistanceService;
        this.materialService = materialService;
    }

    public void register (RegisterRequestUser registerRequestUser) {

        Optional<User> optionalUser = userRepository.findByUserName(registerRequestUser.username());

        if(optionalUser.isPresent()) {
            throw new RuntimeException("User with [%s] username already exists".formatted(registerRequestUser.username()));
        }

        User user = User.builder()
                .userName(registerRequestUser.username())
                .password(passwordEncoder.encode(registerRequestUser.password() ))
                .email(registerRequestUser.email())
                .phoneNumber(registerRequestUser.phoneNumber())
                .role(Role.USER)
                .build();

        user = userRepository.save(user);

        AssistanceService.createDefaultAssistanceList(user);
        MaterialService.createDefaultMaterialList(user);

    }
}
