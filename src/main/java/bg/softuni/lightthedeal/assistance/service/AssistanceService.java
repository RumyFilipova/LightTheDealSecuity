package bg.softuni.lightthedeal.assistance.service;

import bg.softuni.lightthedeal.assistance.entity.Assistance;
import bg.softuni.lightthedeal.assistance.repository.AssistanceRepository;
import bg.softuni.lightthedeal.user.entity.User;
import bg.softuni.lightthedeal.web.DTO.AssistanceServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssistanceService {

    private final AssistanceRepository assistanceRepository;

    @Autowired
    public AssistanceService(AssistanceRepository assistanceRepository) {
        this.assistanceRepository = assistanceRepository;
    }


    public static void createDefaultAssistanceList(User user) {

        // Assistance.builder()
        //  .name(AssistanceServiceRequest.name())
        //  .description(AssistanceServiceRequest.)
//.pricePerUnit()
        //.build();
    }
}
