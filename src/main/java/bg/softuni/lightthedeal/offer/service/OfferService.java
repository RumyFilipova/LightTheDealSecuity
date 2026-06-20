package bg.softuni.lightthedeal.offer.service;

import bg.softuni.lightthedeal.assistance.repository.OfferAssistanceRepository;
import bg.softuni.lightthedeal.assistance.service.OfferAssistanceLineService;
import bg.softuni.lightthedeal.customer.entity.Customer;
import bg.softuni.lightthedeal.customer.repository.CustomerRepository;
import bg.softuni.lightthedeal.materials.entities.OfferMaterialLine;
import bg.softuni.lightthedeal.materials.repository.OfferMaterialLineRepository;
import bg.softuni.lightthedeal.materials.service.OfferMaterialLineService;
import bg.softuni.lightthedeal.offer.entity.Offer;
import bg.softuni.lightthedeal.offer.repository.OfferRepository;
import bg.softuni.lightthedeal.premise.entity.Premise;
import bg.softuni.lightthedeal.premise.repository.PremiseRepository;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.user.service.UserService;
import bg.softuni.lightthedeal.web.DTO.AssistanceLineRequest;
import bg.softuni.lightthedeal.web.DTO.MaterialLineRequest;
import bg.softuni.lightthedeal.web.DTO.OfferServiceRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final CustomerRepository customerRepository;
    private final PremiseRepository premiseRepository;

    private final OfferMaterialLineService offerMaterialService;
    private final OfferAssistanceLineService offerAssistanceService;

    private final OfferAssistanceRepository offerAssistanceRepository;
    private final OfferMaterialLineRepository offerMaterialLineRepository;


    public OfferService(OfferRepository offerRepository, CustomerRepository customerRepository, PremiseRepository premiseRepository, OfferMaterialLineService offerMaterialService, OfferAssistanceLineService offerAssistanceService, OfferAssistanceRepository offerAssistanceRepository, OfferMaterialLineRepository offerMaterialLineRepository) {
        this.offerRepository = offerRepository;
        this.customerRepository = customerRepository;
        this.premiseRepository = premiseRepository;
        this.offerMaterialService = offerMaterialService;
        this.offerAssistanceService = offerAssistanceService;
        this.offerAssistanceRepository = offerAssistanceRepository;
        this.offerMaterialLineRepository = offerMaterialLineRepository;
    }


    // create offer

    public Offer createOffer(OfferServiceRequest request, User user) {

        Customer customer = customerRepository.findByIdAndUsers(request.getCustomerId(), user)
                .orElseThrow(() -> new RuntimeException("Customer %s not found".formatted(request.getCustomerId())));

        Premise premise = premiseRepository.findByIdAndUser(request.getPremiseId(), user)
                .orElseThrow(() -> new RuntimeException("Premise %s not found".formatted(request.getPremiseId())));


        Offer offer = Offer.builder()
                .offerNumber(generateOfferNumber())
                .createdOn(LocalDateTime.now())
                .deadline(request.getDeadline())
                .totalAmount(BigDecimal.ZERO)
                .user(user)
                .customer(customer)
                .premise(premise)
                .build();

        offerRepository.save(offer);

        for (MaterialLineRequest line : request.getMaterials()) {
            offerMaterialService.createOfferMaterialLine(line, offer, user);
        }

        for (AssistanceLineRequest line : request.getAssistants()) {
            offerAssistanceService.createOfferAssistanceLine(line, offer, user);
        }

        BigDecimal totalAmount = offerMaterialService.calculateTotal(offer).add(offerAssistanceService.calculateTotal(offer));

        offer.setTotalAmount(totalAmount);
        return offerRepository.save(offer);
    }


    public Offer getByIdAndUser(UUID id, User user) {

        return offerRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Offer %s not found for the user".formatted(id)));

    }

    //generateOfferNumber
    public String generateOfferNumber() {
        return "OFF-%S-%05d".formatted(LocalDate.now().getYear(), offerRepository.count() + 1);

    }

    // recalculateTotal()
    public Offer recalculateTotalAmount(UUID offerId, User user) {

        Offer offer = getByIdAndUser(offerId, user);
        BigDecimal materialsTotal = offerMaterialService.calculateTotal(offer);
        BigDecimal assistanceTotal = offerAssistanceService.calculateTotal(offer);

        offer.setTotalAmount(materialsTotal.add(assistanceTotal));
        return offerRepository.save(offer);
    }


}
