package bg.softuni.lightthedeal.web;

import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.user.property.UserProperties;
import bg.softuni.lightthedeal.user.service.UserService;
import bg.softuni.lightthedeal.web.DTO.RegisterRequestUser;
import bg.softuni.lightthedeal.web.DTO.UserLoginRequest;
import jakarta.persistence.ManyToMany;
import jakarta.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class HomeController {

    private final UserService userService;
    private final UserProperties userProperties;

    @Autowired
    HomeController(UserService userService, UserProperties userProperties) {
        this.userService = userService;
        this.userProperties = userProperties;
    }

    @GetMapping("/")
    public String getHomePage() {

        return "home";
    }


    @GetMapping("/home")
    public String getMyHomePage() {
        return "home";
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage() {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("userLoginRequest", new UserLoginRequest());
        modelAndView.addObject("registerRequestUser", new RegisterRequestUser());
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @GetMapping("/profile")
    public ModelAndView getUserInitialPage() {
        User currentUser = userService.getByUsername(userProperties.getDefaultUser().getUsername());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");
        modelAndView.addObject("currentUser", currentUser);

        return modelAndView;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterRequestUser registerRequestUser) {
        userService.register(registerRequestUser);

        return "redirect:/login";
    }

}
