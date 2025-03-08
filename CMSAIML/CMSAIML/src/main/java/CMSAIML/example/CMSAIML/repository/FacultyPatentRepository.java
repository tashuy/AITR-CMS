package CMSAIML.example.CMSAIML.repository;

import CMSAIML.example.CMSAIML.Entity.FacultyPatent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyPatentRepository extends JpaRepository<FacultyPatent, Long> {
}
