package bg.softuni.lightthedeal.assistance.service;

import bg.softuni.lightthedeal.assistance.entity.Assistance;

import bg.softuni.lightthedeal.assistance.entity.OfferAssistanceLine;
import bg.softuni.lightthedeal.assistance.repository.AssistanceRepository;
import bg.softuni.lightthedeal.assistance.repository.OfferAssistanceRepository;
import bg.softuni.lightthedeal.offer.entity.Offer;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.web.DTO.AssistanceLineRequest;
import bg.softuni.lightthedeal.web.DTO.AssistanceLineUpdateRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class OfferAssistanceLineService {

    private final AssistanceRepository assistanceRepository;
    private final OfferAssistanceRepository offerAssistanceLineRepository;

    public OfferAssistanceLineService(AssistanceRepository assistanceRepository, OfferAssistanceRepository offerAssistanceRepository, OfferAssistanceRepository offerAssistanceRepository1, OfferAssistanceRepository offerAssistanceLineRepository) {
        this.assistanceRepository = assistanceRepository;

        this.offerAssistanceLineRepository = offerAssistanceLineRepository;
    }


    // build assistance for the offer

    public OfferAssistanceLine createOfferAssistanceLine(AssistanceLineRequest request, Offer offer, User user){

        Assistance requestedAssistance = assistanceRepository.findById(request.getAssistanceId())
                .orElseThrow(()-> new RuntimeException("The requested assistance do not exist in your list"));

        OfferAssistanceLine line = OfferAssistanceLine.builder()
                .assistance(requestedAssistance)
                .quantity(request.getQuantity())
                .priceAtTimeOfOffer(requestedAssistance.getPricePerUnit())
                .description(request.getDescription())
                .offer(offer)
                .user(user)
                .build();

        return offerAssistanceLineRepository.save(line);
    }



    // update price

    public OfferAssistanceLine updateOfferAssistanceLine(AssistanceLineUpdateRequest request, Offer offer, User user) {

        OfferAssistanceLine lineToUpdate = offerAssistanceLineRepository.findByIdAndUser(request.getLineId(), user)
                .orElseThrow(() -> new RuntimeException("The requested input of assistance do not exist in your list"));

        Assistance assistance = assistanceRepository.findByIdAndUser(request.getAssistanceId(), user)
                .orElseThrow(() -> new RuntimeException("The requested assistance do not exist in your list"));

        lineToUpdate.setAssistance(assistance);
        lineToUpdate.setQuantity(request.getQuantity());


        return offerAssistanceLineRepository.save(lineToUpdate);

    }
    // remove assistance

    public void deleteOfferAssistanceLine(UUID id){
OfferAssistanceLine lineToDelete = offerAssistanceLineRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("The requested assistance do not exist in your offer"));
    offerAssistanceLineRepository.delete(lineToDelete);
    }

public BigDecimal calculateTotal(Offer offer){

        return offerAssistanceLineRepository.findAllByOffer(offer)
                .stream()
                .map(OfferAssistanceLine::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
}

}
