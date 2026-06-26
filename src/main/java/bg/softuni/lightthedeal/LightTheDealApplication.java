package bg.softuni.lightthedeal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication()
public class LightTheDealApplication {

    public static void main(String[] args) {
        SpringApplication.run(LightTheDealApplication.class, args);
    }

}
