package bg.softuni.lightthedeal.user.service;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.user.property.UserProperties;
import bg.softuni.lightthedeal.web.DTO.RegisterRequestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import java.util.List;

//@Component
public class UserInit implements ApplicationRunner {

    private final UserService userService;
    private final UserProperties userProperties;

    @Autowired
    public UserInit(UserService userService, UserProperties userProperties) {
        this.userService = userService;
        this.userProperties = userProperties;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<User> users = userService.getAll();

        boolean isUserExist = users.stream()
                .anyMatch(user -> user.getUsername().equals(userProperties.getDefaultUser().getUsername()));

        if (!isUserExist) {

            RegisterRequestUser registerRequestUser = RegisterRequestUser.builder()
                    .username(userProperties.getDefaultUser().getUsername())
                    .password(userProperties.getDefaultUser().getPassword())
                    .confirmPassword(userProperties.getDefaultUser().getPassword())
                    .email(userProperties.getDefaultUser().getEmail())
                    .phoneNumber(userProperties.getDefaultUser().getPhoneNumber())
                    .build();

            userService.register(registerRequestUser);
        }
    }
}
