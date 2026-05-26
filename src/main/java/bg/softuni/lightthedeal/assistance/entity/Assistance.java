package bg.softuni.lightthedeal.assistance.entity;

import bg.softuni.lightthedeal.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "assistance")
public class Assistance {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(name = "price_per_unit",nullable = false)
    private BigDecimal pricePerUnit;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
