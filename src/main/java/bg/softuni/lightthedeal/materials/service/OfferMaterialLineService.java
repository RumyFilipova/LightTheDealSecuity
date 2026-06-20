package bg.softuni.lightthedeal.materials.service;

import bg.softuni.lightthedeal.materials.entities.Material;
import bg.softuni.lightthedeal.materials.entities.OfferMaterialLine;
import bg.softuni.lightthedeal.materials.repository.MaterialRepository;
import bg.softuni.lightthedeal.materials.repository.OfferMaterialLineRepository;
import bg.softuni.lightthedeal.offer.entity.Offer;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.web.DTO.MaterialLineRequest;
import bg.softuni.lightthedeal.web.DTO.MaterialLineUpdateRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;


@Service
public class OfferMaterialLineService {

    private final MaterialRepository materialRepository;
    private final OfferMaterialLineRepository offerMaterialLineRepository;

    public OfferMaterialLineService(MaterialRepository materialRepository, OfferMaterialLineRepository offerMaterialLineRepository) {
        this.materialRepository = materialRepository;
        this.offerMaterialLineRepository = offerMaterialLineRepository;
    }


    //builds OfferMaterial lines from the request

    public OfferMaterialLine createOfferMaterialLine(MaterialLineRequest request, Offer offer, User user) {

        Material requestedMaterial = materialRepository.findByIdAndUser(request.getMaterialId(), user)
                .orElseThrow(() -> new RuntimeException("The requested material do not exist in your list"));

        OfferMaterialLine line = OfferMaterialLine.builder()
                .material(requestedMaterial)
                .quantity(request.getQuantity())
                .priceAtTimeOfOffer(requestedMaterial.getSinglePrice())
                .description(requestedMaterial.getDescription())
                .offer(offer)
                .user(user)
                .build();

        return offerMaterialLineRepository.save(line);
    }

    // update quantity

    public OfferMaterialLine updateOfferMaterialLine(MaterialLineUpdateRequest request, Offer offer, User user) {

        OfferMaterialLine lineToUpdate = offerMaterialLineRepository.findByIdAndUser(request.getLineId(),user)
                .orElseThrow(()-> new RuntimeException("The requested input of material is not found in the offer"));

        Material material = materialRepository.findByIdAndUser(request.getMaterialId(),user)
                .orElseThrow(()-> new RuntimeException("The requested material do not exist in your list"));
        lineToUpdate.setMaterial(material);
        lineToUpdate.setQuantity(request.getQuantity());

        return offerMaterialLineRepository.save(lineToUpdate);
    }

   // remove material
    public void  deleteOfferMaterialLine(UUID id) {
        OfferMaterialLine lineToDelete = offerMaterialLineRepository.findById(id).orElseThrow(()-> new RuntimeException("The requested material do not exist in your offer"));
        offerMaterialLineRepository.delete(lineToDelete);
    }

    // calculate the offer

    public BigDecimal calculateTotal(Offer offer){

        return offerMaterialLineRepository.findAllByOffer(offer)
                .stream()
                .map(OfferMaterialLine::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

}
