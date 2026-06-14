package bg.softuni.lightthedeal.web.controllers;

import bg.softuni.lightthedeal.materials.entities.Category;
import bg.softuni.lightthedeal.materials.entities.Unit;
import bg.softuni.lightthedeal.materials.service.MaterialService;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.user.property.UserProperties;
import bg.softuni.lightthedeal.user.repository.UserRepository;
import bg.softuni.lightthedeal.user.service.UserService;
import bg.softuni.lightthedeal.web.DTO.MaterialServiceRequest;
import bg.softuni.lightthedeal.web.DTO.MaterialUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/material")
public class MaterialController {

    private final MaterialService materialService;
    private final UserService userService;
    private final UserProperties userProperties;

    @Autowired
    public MaterialController(MaterialService materialService, UserService userService, UserProperties userProperties) {
        this.materialService = materialService;
        this.userService = userService;
        this.userProperties = userProperties;
    }

    @GetMapping
    public ModelAndView getMaterial() {
        // TODO: replace hardcoded UUID with logged-in user from session/security
        User user = userService.getByUsername(userProperties.getDefaultUser().getUsername());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("material");

        modelAndView.addObject("materialRequest", new MaterialServiceRequest());
        modelAndView.addObject("materialUpdateRequest", new MaterialUpdateRequest());
        modelAndView.addObject("materialsList", materialService.getAllMaterialForUser(user));
        modelAndView.addObject("category", Category.values());
        modelAndView.addObject("unit", Unit.values());
        modelAndView.addObject("user", user.getId());


        return modelAndView;
    }
    @PostMapping
    public String addMaterial( @ModelAttribute("materialRequest") MaterialServiceRequest materialRequest) {

        User user = userService.getByUsername(userProperties.getDefaultUser().getUsername());
        materialService.createMaterial(materialRequest, user);
        return "redirect:/material";
    }

    /*
    public String addMaterial(){}
    public String updateMaterial(){}
    public String updateMaterialQuantity(){}
    public String updateMaterialPrice(){}
    public String removeMaterial(){}*/
}
