package bg.softuni.lightthedeal.user.property;


import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "users")
public class UserProperties {

    private DefaultUser defaultUser;
    private String testProperty;

    @Data
    public static class DefaultUser{
        private String username;
        private String password;
        private String email;
        private String phoneNumber;

    }

  /*  @PostConstruct
    public void test (){
        System.out.println();
    }*/

}
