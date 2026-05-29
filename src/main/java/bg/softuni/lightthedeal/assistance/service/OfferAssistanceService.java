package bg.softuni.lightthedeal.assistance.service;

import bg.softuni.lightthedeal.assistance.entity.Assistance;
import bg.softuni.lightthedeal.assistance.entity.OfferAssistance;
import bg.softuni.lightthedeal.assistance.repository.AssistanceRepository;
import bg.softuni.lightthedeal.assistance.repository.OfferAssistanceRepository;
import bg.softuni.lightthedeal.offer.entity.Offer;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.web.DTO.OfferAssistanceLine;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class OfferAssistanceService {

    private final AssistanceRepository assistanceRepository;
    private final OfferAssistanceRepository offerAssistanceRepository;


    public OfferAssistanceService(AssistanceRepository assistanceRepository, OfferAssistanceRepository offerAssistanceRepository) {
        this.assistanceRepository = assistanceRepository;
        this.offerAssistanceRepository = offerAssistanceRepository;
    }

    // build assistance for the offer

    public List<OfferAssistance> createAssistanceLine(
            Offer offer, List<OfferAssistanceLine> lines, User user){

        List<OfferAssistance> offeredAssistanceLine = lines.stream()
                .map(line->{
                    Assistance assistanceToAdd = assistanceRepository.findByIdAndUser(line.assistanceId(),user)
                            .orElseThrow(()-> new RuntimeException("Assistance %s is not supported for the user %s".formatted(line.assistanceId(),user.getUserName())));

                    return OfferAssistance.builder()
                            .offer(offer)
                            .user(user)
                            .assistance(assistanceToAdd)
                            .quantity(line.quantity().doubleValue())
                            .priceAtTimeOfOffer(assistanceToAdd.getPricePerUnit())
                            .build();
                })
                .toList();

        return offerAssistanceRepository.saveAll(offeredAssistanceLine);
    }


    // update assistance
    public OfferAssistance updateAssistance (UUID id, Double quantity, User user){

        OfferAssistance oaLine = offerAssistanceRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("The particular request of assistance %s was not found in the offer".formatted(id)));
    oaLine.setQuantity(oaLine.getQuantity() + quantity);
        return offerAssistanceRepository.save(oaLine);
    }

    // update price

    public OfferAssistance updatePrice (UUID id, BigDecimal newPrice, User user){

        OfferAssistance oaLine = offerAssistanceRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("The particular request of assistance %s was not found in the offer".formatted(id)));
        oaLine.setPriceAtTimeOfOffer(newPrice);

        return offerAssistanceRepository.save(oaLine);
    }

    // remove assistance

    public void removeAssistanceLine(UUID id, User user){
        OfferAssistance line = offerAssistanceRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("The particular assistance %s was not found in the offer and it is not possible to be delete".formatted(id)));

        offerAssistanceRepository.delete(line);
    }
    // calculate the offer
    public BigDecimal calculateTotal(Offer offer){

        return assistanceRepository.findAllByOffer(offer)
                .stream()
                .map(OfferAssistance::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
