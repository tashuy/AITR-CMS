package CMSAIML.example.CMSAIML.repository;

import CMSAIML.example.CMSAIML.Entity.StudentSports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentSportsRepository extends JpaRepository<StudentSports, Long> {
}
