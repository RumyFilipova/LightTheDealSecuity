package bg.softuni.lightthedeal.web;

import bg.softuni.lightthedeal.materials.service.MaterialService;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.user.service.UserService;
import bg.softuni.lightthedeal.web.DTO.AssistanceServiceRequest;
import bg.softuni.lightthedeal.web.DTO.MaterialServiceRequest;
import bg.softuni.lightthedeal.web.DTO.MaterialUpdateRequest;
import org.hibernate.validator.cfg.defs.Mod10CheckDef;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/material")
public class MaterialController {

    private final MaterialService materialService;
    private final UserService userService;

    public MaterialController(MaterialService materialService, UserService userService) {
        this.materialService = materialService;
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView getMaterial(Principal principal) {
        // TODO: replace hardcoded UUID with logged-in user from session/security
        User user = userService.getById(UUID.fromString("my-user-id"));

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("material");

        modelAndView.addObject("materialRequest", new MaterialServiceRequest());
        modelAndView.addObject("materialUpdateRequest", new MaterialUpdateRequest());
        modelAndView.addObject("materialsList", materialService.getAllMaterialForUser(user));
        modelAndView.addObject("user", user.getId());


        return modelAndView;
    }
    /*
    public String addMaterial(){}
    public String updateMaterial(){}
    public String updateMaterialQuantity(){}
    public String updateMaterialPrice(){}
    public String removeMaterial(){}*/
}
