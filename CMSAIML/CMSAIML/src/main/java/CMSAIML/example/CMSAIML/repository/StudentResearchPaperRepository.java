package CMSAIML.example.CMSAIML.repository;

import CMSAIML.example.CMSAIML.Entity.StudentResearchPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentResearchPaperRepository extends JpaRepository<StudentResearchPaper, Long> {
}
