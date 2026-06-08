package bg.softuni.lightthedeal.web;

import bg.softuni.lightthedeal.assistance.entity.Assistance;
import bg.softuni.lightthedeal.assistance.service.AssistanceService;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.user.service.UserService;
import bg.softuni.lightthedeal.web.DTO.AssistanceServiceRequest;
import bg.softuni.lightthedeal.web.DTO.AssistanceUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/assistance")
public class AssistanceController {
    private final AssistanceService assistanceService;
    private final UserService userService;

    @Autowired
    public AssistanceController(AssistanceService assistanceService, UserService userService) {
        this.assistanceService = assistanceService;
        this.userService = userService;
    }


    // // GET — empty form + list of existing services
    @GetMapping
    public ModelAndView getAssistanceInitialPage() {

        // TODO: replace hardcoded UUID with logged-in user from session/security
        User user =userService.getById(UUID.fromString("my-user-id"));

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("assistance");
        modelAndView.addObject("assistanceRequest", new AssistanceServiceRequest());
        modelAndView.addObject("assistanceUpdateRequest", new AssistanceUpdateRequest());
        modelAndView.addObject("assistanceList", assistanceService.getAllAssistanceForUSer(user));
        modelAndView.addObject("user",user.getId());

        return modelAndView;
    }


   /*
    public String addAssistance(){}
    public String updateAssistance(){}
    public String updateAssistanceQuantity(){}
    public String updateAssistancePrice(){}
    public String removeAssistance(){}*/
}
