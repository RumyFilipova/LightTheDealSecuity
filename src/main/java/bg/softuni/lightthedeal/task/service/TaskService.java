package bg.softuni.lightthedeal.task.service;

import bg.softuni.lightthedeal.task.entity.Task;
import bg.softuni.lightthedeal.task.repository.TaskRepository;
import bg.softuni.lightthedeal.user.entity.Role;
import bg.softuni.lightthedeal.user.service.UserService;
import bg.softuni.lightthedeal.web.DTO.TaskCreateRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;


    }

    public Task saveTask(@Valid TaskCreateRequest request, UUID id) {

        Task task = new Task();

        task.setEmployeeFirstName(request.getStaffFirstName());
        task.setEmployeeLastName(request.getStaffLastName());
        task.setRoleToTake(Role.valueOf(request.getStaffRole()));
        //task.setPremise(request.getStaffPremise());

        taskRepository.save(task);
        return task;
    }
}
