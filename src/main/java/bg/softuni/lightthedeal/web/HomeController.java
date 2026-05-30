package bg.softuni.lightthedeal.web;

import bg.softuni.lightthedeal.web.DTO.UserLoginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")

public class HomeController {

    @GetMapping("/")
public String getHomePage(){

        return "home";
    }

    @GetMapping("/login")
public String getLoginPage(){
        return "login";
    }

    @GetMapping("/register")
public String getRegisterPage(){

        return "register";
    }

    @GetMapping("/user")
public String getUserInitialPage()
 {
        return "user";
 }

}
