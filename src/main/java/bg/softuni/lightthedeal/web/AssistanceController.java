package bg.softuni.lightthedeal.web;

import bg.softuni.lightthedeal.assistance.entity.Assistance;
import bg.softuni.lightthedeal.web.DTO.AssistanceServiceRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/assistance")
public class AssistanceController {

    @GetMapping
    public ModelAndView getAssistanceInitialPage()
    {
        ModelAndView modelAndView =  new ModelAndView();

        modelAndView.setViewName("assistance");
        modelAndView.addObject("assistanceRequest",new AssistanceServiceRequest());

        return modelAndView;
    }


   /*
    public String addAssistance(){}
    public String updateAssistance(){}
    public String updateAssistanceQuantity(){}
    public String updateAssistancePrice(){}
    public String removeAssistance(){}*/
}
