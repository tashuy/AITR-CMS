package CMSAIML.example.CMSAIML.repository;

import CMSAIML.example.CMSAIML.Entity.FacultyConference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyConferenceRepository extends JpaRepository<FacultyConference, Long> {
}
