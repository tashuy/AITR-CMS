package CMSAIML.example.CMSAIML.repository;

import CMSAIML.example.CMSAIML.Entity.FacultyFdp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyFdpRepository extends JpaRepository<FacultyFdp, Long> {
}
