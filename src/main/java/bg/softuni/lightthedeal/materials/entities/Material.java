package bg.softuni.lightthedeal.materials.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "materials")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;// name(cable, circuit breaker)

    @Column(nullable = false)
    private String type;// type(1,5 sq mm , 16A,2A )

    @Column
    private String description;//additional clarification such as type of assembly

    @Column
    private String brand;// Schneider, Siemens, Muller

    @Column(name = "single_price")
    private Double singlePrice;
}
