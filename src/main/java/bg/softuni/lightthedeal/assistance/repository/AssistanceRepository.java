package bg.softuni.lightthedeal.assistance.repository;
import bg.softuni.lightthedeal.assistance.entity.Assistance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AssistanceRepository extends JpaRepository<Assistance, UUID> {
}
