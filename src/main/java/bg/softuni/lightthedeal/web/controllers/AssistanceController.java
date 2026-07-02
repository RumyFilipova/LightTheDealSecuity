package bg.softuni.lightthedeal.web.controllers;
import bg.softuni.lightthedeal.assistance.entity.Category;
import bg.softuni.lightthedeal.assistance.entity.Unit;
import bg.softuni.lightthedeal.assistance.service.AssistanceService;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.user.service.AuthenticationUserDetails;
import bg.softuni.lightthedeal.user.service.UserService;
import bg.softuni.lightthedeal.web.DTO.AssistanceServiceRequest;
import bg.softuni.lightthedeal.web.DTO.AssistanceUpdateRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    public ModelAndView getAssistanceInitialPage(@AuthenticationPrincipal AuthenticationUserDetails principal) {

        User user = userService.getById(principal.getId());

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
    //@ModelAttribute("assistanceRequest")
    public ModelAndView addAssistance(@Valid AssistanceServiceRequest assistanceServiceRequest, BindingResult bindingResult, HttpSession session) {

        UUID userId =(UUID) session.getAttribute("userId");
        User user = userService.getById(userId);

        if(bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView();

            modelAndView.setViewName("assistance");
            modelAndView.addObject("assistanceRequest", new AssistanceServiceRequest());
            modelAndView.addObject("assistanceUpdateRequest", assistanceServiceRequest);
            modelAndView.addObject("assistanceList", assistanceService.getAllAssistanceResponsesForUser(user));
            modelAndView.addObject("category", Category.values());
            modelAndView.addObject("unit", Unit.values());
            modelAndView.addObject("user", user.getId());
            return modelAndView;

        }

        assistanceService.createAssistance(assistanceServiceRequest , user);
        return new ModelAndView("redirect:/assistance");
    }

    @PostMapping("/{id}/delete")
    public String deleteAssistance(@PathVariable UUID id,HttpSession session) {

        UUID userId =(UUID) session.getAttribute("userId");

        User user = userService.getById(userId);
        assistanceService.deleteAssistance(id, user);
        return "redirect:/assistance";
    }

    @PostMapping("/{id}/update")
    //@ModelAttribute("assistanceUpdateRequest")
    public ModelAndView updateAssistance(@PathVariable UUID id, @Valid AssistanceUpdateRequest request, BindingResult bindingResult,HttpSession session) {


        UUID userId =(UUID) session.getAttribute("userId");
        User user = userService.getById(userId);

        if(bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView();

            modelAndView.setViewName("assistance");
            modelAndView.addObject("assistanceRequest", new AssistanceServiceRequest());
            modelAndView.addObject("assistanceUpdateRequest", request);
            modelAndView.addObject("assistanceList", assistanceService.getAllAssistanceResponsesForUser(user));
            modelAndView.addObject("category", Category.values());
            modelAndView.addObject("unit", Unit.values());
            modelAndView.addObject("user", user.getId());
            return modelAndView;
        }


        assistanceService.updateAssistance(request, id,user);

        return new ModelAndView("redirect:/assistance");
    }

    
}
