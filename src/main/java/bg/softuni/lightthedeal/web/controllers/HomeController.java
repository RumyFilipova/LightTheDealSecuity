package bg.softuni.lightthedeal.web.controllers;

import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.user.property.UserProperties;
import bg.softuni.lightthedeal.user.service.UserService;
import bg.softuni.lightthedeal.web.DTO.RegisterRequestUser;
import bg.softuni.lightthedeal.web.DTO.UserLoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping({"/","/home"})
    public String getHomePage() {

        return "home";
    }


    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("login");
        mv.addObject("user", new User());
        mv.addObject("userLoginRequest", new UserLoginRequest());
        mv.addObject("registerRequestUser", new RegisterRequestUser());

        return mv;
    }


    @GetMapping("/profile")
    public ModelAndView getUserInitialPage() {
        
        User currentUser = userService.getByUsername(userProperties.getDefaultUser().getUsername());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");
        modelAndView.addObject("currentUser", currentUser);

        return modelAndView;
    }

    @PostMapping("/login")

    public String login (@ModelAttribute UserLoginRequest userLoginRequest) {

        User user = userService.getByUsername(userProperties.getDefaultUser().getUsername());
        return "redirect:/profile";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterRequestUser registerRequestUser) {
        userService.register(registerRequestUser);

        return "redirect:/login";
    }

}
