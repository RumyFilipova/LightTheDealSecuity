package bg.softuni.lightthedeal.materials.service;

import bg.softuni.lightthedeal.materials.entities.Material;
import bg.softuni.lightthedeal.materials.entities.OfferMaterial;
import bg.softuni.lightthedeal.materials.repository.MaterialRepository;
import bg.softuni.lightthedeal.materials.repository.OfferMaterialRepository;
import bg.softuni.lightthedeal.offer.entity.Offer;
import bg.softuni.lightthedeal.offer.repository.OfferRepository;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.web.DTO.OfferMaterialLine;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class OfferMaterialService {

private final MaterialRepository materialRepository;
private final OfferMaterialRepository offerMaterialRepository;


    public OfferMaterialService(MaterialRepository materialRepository, OfferMaterialRepository offerMaterialRepository) {
        this.materialRepository = materialRepository;
        this.offerMaterialRepository = offerMaterialRepository;
    }

    //builds OfferMaterial lines from the request

    public List<OfferMaterial> createMaterialLines(Offer offer, List<OfferMaterialLine>lines, User user){

        List<OfferMaterial> offerMaterials = lines.stream()
                .map(line ->{
                    Material materialToAdd = materialRepository.findByIdAndUser(line.materialId(), user)
                            .orElseThrow(()-> new RuntimeException("Material %s not found for user %s".formatted(line.materialId(),user.getUserName())));

                    return OfferMaterial.builder()
                            .offer(offer)
                            .user(user)
                            .material(materialToAdd)
                            .quantity(line.quantity())
                            .priceAtTimeOfOffer(materialToAdd.getSinglePrice())
                            .build();
                })
                .toList();

        return offerMaterialRepository.saveAll(offerMaterials);
    }

    // update quantity
    public OfferMaterial updateQuantity (UUID id, Double newQuantity, User user){

        OfferMaterial line = offerMaterialRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("The particular request of material %s was not found in the offer".formatted(id)));

        line.setQuantity(line.getQuantity()+newQuantity);
        return offerMaterialRepository.save(line);
    }
// update price
    public OfferMaterial updatePrice (UUID id, BigDecimal newPrice, User user){

        OfferMaterial line = offerMaterialRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("The particular request of material %s was not found in the offer".formatted(id)));

        line.setPriceAtTimeOfOffer(newPrice);
        return offerMaterialRepository.save(line);
    }

    // remove material
    public void deleteMaterial(UUID id, User user){
        OfferMaterial line = offerMaterialRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("The particular material %s was not found in the offer and it is not possible to be delete".formatted(id)));

        offerMaterialRepository.delete(line);
    }

    // calculate the offer

    public BigDecimal calculateTotal(Offer offer){

        return offerMaterialRepository.findAllByOffer(offer)
                .stream()
                .map(OfferMaterial::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

}
