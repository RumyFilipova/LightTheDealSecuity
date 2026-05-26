package bg.softuni.lightthedeal.premise.service;

import bg.softuni.lightthedeal.customer.entity.Customer;
import bg.softuni.lightthedeal.customer.service.CustomerService;
import bg.softuni.lightthedeal.premise.entity.Premise;
import bg.softuni.lightthedeal.premise.repository.PremiseRepository;
import bg.softuni.lightthedeal.user.entity.User;
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

        Customer customer = customerService.getByIdAndUser(premiseServiceRequest.customerId(),user);

        Premise premise = Premise.builder()
                .customer(customer)
                .name(premiseServiceRequest.name())
                .address(premiseServiceRequest.address())
                .description(premiseServiceRequest.description())
        .build();

        return premiseRepository.save(premise);
    }

    public List<Premise> getAllByCustomer(UUID id, User user){

        Customer customer =customerService.getByIdAndUser(id,user);

        return premiseRepository.findAllByCustomer(customer);

    }

    public Premise getByIdAndUser(UUID id, User user){

        Premise premise =premiseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Premise %s not found".formatted(id)));

        return premise;
    }

    // UPDATE

    public Premise updatePremise(PremiseUpdateRequest updateRequest, UUID id, User user){

        Premise premise = getByIdAndUser(id, user);

        premise.setName(updateRequest.name());
        premise.setAddress(updateRequest.address());
        premise.setDescription(updateRequest.description());

        return premiseRepository.save(premise);
    }

    public void  deletePremise(UUID id, User user){
        Premise premise = getByIdAndUser(id, user);

        // Detach from user before deleting
        user.setPremise(null);
        userRepository.save(user);

      premiseRepository.delete(premise);

    }
}
