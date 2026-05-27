package bg.softuni.lightthedeal.web;

import bg.softuni.lightthedeal.web.DTO.UserLoginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {


    @GetMapping("/")
        public String index(){
        return "home";
    }

    @GetMapping("/login")
    public String getLoginPage(){

        /*UserLoginRequest userLoginRequest = new UserLoginRequest();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        mv.addObject("userLoginRequest",userLoginRequest());*/
        return "login";

    }

    @GetMapping("/register")
    public String getRegisterPage(){
        return "register";
    }

}
