package CMSAIML.example.CMSAIML.repository;

import CMSAIML.example.CMSAIML.Entity.FacultyResearchPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyResearchPaperRepository extends JpaRepository<FacultyResearchPaper, Long> {
}
