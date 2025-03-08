package CMSAIML.example.CMSAIML.repository;

import CMSAIML.example.CMSAIML.Entity.StudentCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCertificateRepository extends JpaRepository<StudentCertificate, Long> {
}
