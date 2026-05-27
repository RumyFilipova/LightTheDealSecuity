package bg.softuni.lightthedeal.materials.repository;

import bg.softuni.lightthedeal.materials.entities.Material;
import bg.softuni.lightthedeal.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MaterialRepository extends JpaRepository<Material, UUID> {

    List<Material> findAllByUser(User user);

    Optional <Material> findByIdAndUser(UUID id, User user);
}
