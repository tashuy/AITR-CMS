package CMSAIML.example.CMSAIML.repository;

import CMSAIML.example.CMSAIML.Entity.FacultyAward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyAwardRepository extends JpaRepository<FacultyAward, Long> {
}
