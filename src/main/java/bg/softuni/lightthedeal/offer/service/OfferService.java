package bg.softuni.lightthedeal.offer.service;

import bg.softuni.lightthedeal.assistance.repository.OfferAssistanceRepository;
import bg.softuni.lightthedeal.assistance.service.OfferAssistanceService;
import bg.softuni.lightthedeal.customer.entity.Customer;
import bg.softuni.lightthedeal.customer.repository.CustomerRepository;
import bg.softuni.lightthedeal.materials.service.OfferMaterialService;
import bg.softuni.lightthedeal.offer.entity.Offer;
import bg.softuni.lightthedeal.offer.repository.OfferRepository;
import bg.softuni.lightthedeal.order.repository.OrderRepository;
import bg.softuni.lightthedeal.premise.entity.Premise;
import bg.softuni.lightthedeal.premise.repository.PremiseRepository;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.web.DTO.CreateOfferRequest;
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

    private final OfferMaterialService offerMaterialService;
    private final OfferAssistanceService offerAssistanceService;

    public OfferService(OfferRepository offerRepository, CustomerRepository customerRepository, PremiseRepository premiseRepository, OfferMaterialService offerMaterialService, OfferAssistanceService offerAssistanceService) {
        this.offerRepository = offerRepository;
        this.customerRepository = customerRepository;
        this.premiseRepository = premiseRepository;
        this.offerMaterialService = offerMaterialService;
        this.offerAssistanceService = offerAssistanceService;
    }

    // create offer
    public Offer createOffer(CreateOfferRequest request, User user) {

        Customer customer = customerRepository.findByIdAndUsers(request.customerId(), user)
                .orElseThrow(() -> new RuntimeException("Customer %s not found".formatted(request.customerId())));

        Premise premise = premiseRepository.findByIdAndUser(request.premiseId(), user)
                .orElseThrow(() -> new RuntimeException("Premise %s not found".formatted(request.premiseId())));

        Offer offer = Offer.builder()
                .user(user)
                .customer(customer)
                .premise(premise)
                .createdOn(LocalDateTime.now())
                .offerNumber(generateOfferNumber())
                .totalAmount(BigDecimal.ZERO)
                .build();
        offer = offerRepository.save(offer);

        offerMaterialService.createMaterialLines(offer, request.materials(), user);
        offerAssistanceService.createAssistanceLine(offer, request.assistants(), user);

        BigDecimal materialsTotal = offerMaterialService.calculateTotal(offer);
        BigDecimal assistanceTotal = offerAssistanceService.calculateTotal(offer);
        offer.setTotalAmount(materialsTotal.add(assistanceTotal));

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
