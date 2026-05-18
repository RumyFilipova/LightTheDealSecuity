package bg.softuni.lightthedeal.materials.service;

import bg.softuni.lightthedeal.materials.repository.MaterialRepository;
import bg.softuni.lightthedeal.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;

    @Autowired
    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }


    public static void createDefaultMaterialList(User user) {
    }
}
