package bg.softuni.lightthedeal.web.controllers;

import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.user.property.UserProperties;
import bg.softuni.lightthedeal.user.service.UserService;
import bg.softuni.lightthedeal.web.DTO.RegisterRequestUser;
import bg.softuni.lightthedeal.web.DTO.UserLoginRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller

public class HomeController {

    private final UserService userService;
    private final UserProperties userProperties;
    private final HttpSession session;

    @Autowired
    HomeController(UserService userService, UserProperties userProperties, HttpSession httpSession, HttpSession session) {
        this.userService = userService;
        this.userProperties = userProperties;
        this.session = session;
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
    public ModelAndView getUserInitialPage(HttpSession session) {

        UUID userId=(UUID) session.getAttribute("userId");
        User currentUser = userService.getById(userId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");
        modelAndView.addObject("currentUser", currentUser);

        return modelAndView;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @PostMapping("/login")

    public ModelAndView login (@Valid UserLoginRequest loginRequest, BindingResult bindingResult, HttpSession session) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("login");
        }

        User user = userService.login(loginRequest);
        session.setAttribute("userId",user.getId());

        return new ModelAndView("redirect:/profile");
    }

    @PostMapping("/register")

    public String register(@ModelAttribute RegisterRequestUser registerRequestUser) {
        userService.register(registerRequestUser);

        return "redirect:/login";
    }

}
