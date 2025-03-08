package CMSAIML.example.CMSAIML.repository;

import CMSAIML.example.CMSAIML.Entity.StudentInternship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentInternshipRepository extends JpaRepository<StudentInternship, Long> {
}
