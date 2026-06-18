package bg.softuni.lightthedeal.assistance.service;
import bg.softuni.lightthedeal.assistance.entity.Assistance;
import bg.softuni.lightthedeal.assistance.repository.AssistanceRepository;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.web.DTO.AssistanceServiceRequest;
import bg.softuni.lightthedeal.web.DTO.AssistanceServiceResponse;
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
                .user(user)
                .name(assistanceServiceRequest.getName())
                .category(assistanceServiceRequest.getCategory())
                .description(assistanceServiceRequest.getDescription())
                .unit(assistanceServiceRequest.getUnit())
                .pricePerUnit(assistanceServiceRequest.getPricePerUnit())
                .durationMinutes(assistanceServiceRequest.getDurationMinutes())
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

        assistance.setName(assistanceUpdateRequest.getName());
        assistance.setDescription(assistanceUpdateRequest.getActivityDescription());
        assistance.setPricePerUnit(assistanceUpdateRequest.getPricePerUnit());
        assistance.setCategory(assistanceUpdateRequest.getCategory());
        assistance.setUnit(assistanceUpdateRequest.getUnit());

        return assistanceRepository.save(assistance);
    }

    //delete

    public void deleteAssistance(UUID id, User user) {
        Assistance assistance = getByIdAndUser(id,user);
        assistanceRepository.delete(assistance);
    }

    public List<AssistanceServiceResponse> getAllAssistanceResponsesForUser(User user) {

        return assistanceRepository.findAllByUser(user)
                .stream()
                .map(this::response)
                .toList();
    }

    private AssistanceServiceResponse response(Assistance assistance) {

        return AssistanceServiceResponse.builder()
                .id(assistance.getId())
                .name(assistance.getName())
                .activityDescription(assistance.getDescription())
                .pricePerUnit(assistance.getPricePerUnit())
                .category(assistance.getCategory())
                .unit(assistance.getUnit())
                .build();
    }
}
