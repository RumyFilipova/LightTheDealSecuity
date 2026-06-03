package bg.softuni.lightthedeal.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/assistance")
public class AssistanceController {

    @GetMapping
    public String getAssistanceInitialPage()
    {
        return "assistance";
    }


   /*
    public String addAssistance(){}
    public String updateAssistance(){}
    public String updateAssistanceQuantity(){}
    public String updateAssistancePrice(){}
    public String removeAssistance(){}*/
}
