package bg.softuni.lightthedeal.task.entity;

import bg.softuni.lightthedeal.premise.entity.Premise;
import bg.softuni.lightthedeal.user.entity.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "employee_first_name",nullable = false)
    private String employeeFirstName;

    @Column(name = "employee_last_name",nullable = false)
    private String employeeLastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_to_take")
    private Role roleToTake;

    @OneToMany
    private List <Premise> premises;
}
