package bg.softuni.lightthedeal.web.controllers;

import bg.softuni.lightthedeal.assistance.entity.Category;
import bg.softuni.lightthedeal.assistance.entity.Unit;
import bg.softuni.lightthedeal.assistance.service.AssistanceService;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.user.property.UserProperties;
import bg.softuni.lightthedeal.user.service.UserService;
import bg.softuni.lightthedeal.web.DTO.AssistanceServiceRequest;
import bg.softuni.lightthedeal.web.DTO.AssistanceUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/assistance")
public class AssistanceController {
    private final AssistanceService assistanceService;
    private final UserService userService;
    private final UserProperties userProperties;

    @Autowired
    public AssistanceController(AssistanceService assistanceService, UserService userService, UserProperties userProperties) {
        this.assistanceService = assistanceService;
        this.userService = userService;
        this.userProperties = userProperties;
    }


    // // GET — empty form + list of existing services
    @GetMapping
    public ModelAndView getAssistanceInitialPage() {

        // TODO: replace hardcoded UUID with logged-in user from session/security
        User user = userService.getByUsername(userProperties.getDefaultUser().getUsername());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("assistance");
        modelAndView.addObject("assistanceRequest", new AssistanceServiceRequest());
        modelAndView.addObject("assistanceUpdateRequest", new AssistanceUpdateRequest());
        modelAndView.addObject("assistanceList", assistanceService.getAllAssistanceResponsesForUser(user));
        modelAndView.addObject("category", Category.values());
        modelAndView.addObject("unit", Unit.values());
        modelAndView.addObject("user", user.getId());

        return modelAndView;
    }

    @PostMapping
    public String addAssistance(@ModelAttribute("assistanceRequest") AssistanceServiceRequest assistanceServiceRequest) {

        User user = userService.getByUsername(userProperties.getDefaultUser().getUsername());

        assistanceService.createAssistance(assistanceServiceRequest , user);
        return "redirect:/assistance";
    }

    @PostMapping("/{id}/delete")
    public String deleteAssistance(@PathVariable UUID id) {

        // replace on session
        User user = userService.getByUsername(userProperties.getDefaultUser().getUsername());
        assistanceService.deleteAssistance(id, user);
        return "redirect:/assistance";
    }

    @PostMapping("/{id}/update")
    public String updateAssistance(@PathVariable UUID id,@ModelAttribute("assistanceUpdateRequest")  AssistanceUpdateRequest request){

        User user = userService.getByUsername(userProperties.getDefaultUser().getUsername());

        assistanceService.updateAssistance(request, id,user);

        return "redirect:/assistance";
    }

    
}
