package bg.softuni.lightthedeal.assistance.service;

import bg.softuni.lightthedeal.assistance.entity.Assistance;
import bg.softuni.lightthedeal.assistance.repository.AssistanceRepository;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.web.DTO.AssistanceServiceRequest;
import bg.softuni.lightthedeal.web.DTO.AssistanceUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AssistanceService {

    private final AssistanceRepository assistanceRepository;

    @Autowired
    public AssistanceService(AssistanceRepository assistanceRepository) {
        this.assistanceRepository = assistanceRepository;
    }

    public  void createAssistance(AssistanceServiceRequest assistanceServiceRequest, User user) {
        Assistance assistance = Assistance.builder()
                .name(assistanceServiceRequest.name())
                .description(assistanceServiceRequest.activityDescription())
                .pricePerUnit(assistanceServiceRequest.pricePerUnit())
                        .build();
assistanceRepository.save(assistance);
    }

    public List<Assistance> getAllAssistanceForUSer(User user) {

        return assistanceRepository.findAllByUser(user);
    }
    public Assistance getByIdAndUser(UUID id, User user) {
        Assistance assistance = assistanceRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Assistance %s not found".formatted(id)));
        return assistance;
    }

    // Update price via DTO
    public Assistance updateAssistance(AssistanceUpdateRequest assistanceUpdateRequest, UUID id,User user) {
        Assistance assistance = getByIdAndUser(id,user);

        assistance.setName(assistanceUpdateRequest.name());
        assistance.setDescription(assistanceUpdateRequest.activityDescription());
        assistance.setPricePerUnit(assistanceUpdateRequest.pricePerUnit());

        return assistanceRepository.save(assistance);
    }

    //delete

    public void deleteAssistance(UUID id, User user) {
        Assistance assistance = getByIdAndUser(id,user);
        assistanceRepository.delete(assistance);
    }
}
