package CMSAIML.example.CMSAIML.repository;

import CMSAIML.example.CMSAIML.Entity.StudentPlacement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentPlacementRepository extends JpaRepository<StudentPlacement, Long> {
}
