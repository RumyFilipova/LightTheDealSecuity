package bg.softuni.lightthedeal.materials.service;

import bg.softuni.lightthedeal.materials.entities.Material;
import bg.softuni.lightthedeal.materials.repository.MaterialRepository;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.web.DTO.MaterialServiceRequest;
import bg.softuni.lightthedeal.web.DTO.MaterialUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MaterialService {
private final  MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }


    public void createMaterial(MaterialServiceRequest materialServiceRequest, User user) {
    Material material = Material.builder()
            .user(user)
            .name(materialServiceRequest.getName())
            .type(materialServiceRequest.getType())
            .description(materialServiceRequest.getDescription())
            .brand(materialServiceRequest.getBrand())
            .singlePrice(materialServiceRequest.getSinglePrice())
            .build();

        materialRepository.save(material);

    }


    //getAllForUser
    public List<Material> getAllMaterialForUser(User user) {

        return materialRepository.findAllByUser(user);
    }


    public Material getByIdAndUser(UUID id, User user) {

        Material material = materialRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Premise %s not found".formatted(id)));

        return material;
    }

    // Update price via DTO
    public Material updateMaterial (MaterialUpdateRequest request,UUID id, User user){

        Material material = getByIdAndUser(id,user);

        material.setName(request.getName());
        material.setType(request.getType());
        material.setBrand(request.getBrand());
        material.setDescription(request.getDescription());
        material.setSinglePrice(request.getSinglePrice());

       return  materialRepository.save(material);
    }
    //delete
    public void deleteMaterial(UUID id, User user) {
        Material material = getByIdAndUser(id,user);

        materialRepository.delete(material);
    }
}
