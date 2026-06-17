package bg.softuni.lightthedeal.premise.service;

import bg.softuni.lightthedeal.customer.entity.Customer;
import bg.softuni.lightthedeal.customer.service.CustomerService;
import bg.softuni.lightthedeal.premise.entity.Premise;
import bg.softuni.lightthedeal.premise.repository.PremiseRepository;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.web.DTO.PremiceServiceResponse;
import bg.softuni.lightthedeal.web.DTO.PremiseServiceRequest;
import bg.softuni.lightthedeal.web.DTO.PremiseUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PremiseService {
    private final CustomerService customerService;
    private final PremiseRepository premiseRepository;

    public PremiseService(CustomerService customerService, PremiseRepository premiseRepository) {
        this.customerService = customerService;
        this.premiseRepository = premiseRepository;
    }

// create

    public Premise cretatePremise(PremiseServiceRequest premiseServiceRequest, User user) {

        Customer customer = customerService.getByIdAndUser(premiseServiceRequest.getCustomerId(), user);


        Premise premise = Premise.builder()
                .customer(customer)
                .name(premiseServiceRequest.getName())
                .type(premiseServiceRequest.getType())
                .address(premiseServiceRequest.getAddress())
                .area(premiseServiceRequest.getArea())
                .description(premiseServiceRequest.getDescription())
                .user(user)
                .build();

        return premiseRepository.save(premise);
    }

    public List<Premise> getAllByCustomer(UUID id, User user) {

        Customer customer = customerService.getByIdAndUser(id, user);

        return premiseRepository.findAllByCustomer(customer);

    }

    public Premise getByIdAndUser(UUID id, User user) {

        Premise premise = premiseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Premise %s not found".formatted(id)));

        return premise;
    }

    public List<Premise> getAllForUser(User user) {
        return premiseRepository.findAllByUser(user);
    }

    // UPDATE

    public Premise updatePremise(PremiseUpdateRequest updateRequest, UUID id, User user) {

        Premise premise = getByIdAndUser(id, user);

        premise.setName(updateRequest.getName());
        premise.setAddress(updateRequest.getAddress());
        premise.setDescription(updateRequest.getDescription());

        return premiseRepository.save(premise);
    }


    public void deletePremise(UUID id, User user) {
        Premise premise = getByIdAndUser(id, user);

        premiseRepository.delete(premise);
    }

    public List<PremiceServiceResponse> getAllPremisesResponsesForTheUser(User user){

        return premiseRepository.findAllByUser(user)
                .stream()
                .map(this::response)
                .toList();

    }

    private PremiceServiceResponse response (Premise premise) {

        return PremiceServiceResponse.builder()
                .id(premise.getId())
                .name(premise.getName())
                .type(premise.getType())
                .address(premise.getAddress())
                .customerId(premise.getCustomer().getId())
                .customerName(premise.getCustomer().getFirstName() + " " + premise.getCustomer().getLastName())
                .area(premise.getArea())
                .description(premise.getDescription())
                .build();
    }

}
