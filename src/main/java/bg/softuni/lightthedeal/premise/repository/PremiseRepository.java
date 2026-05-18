package bg.softuni.lightthedeal.premise.repository;
import bg.softuni.lightthedeal.premise.entity.Premise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PremiseRepository extends JpaRepository<Premise, UUID> {
}
