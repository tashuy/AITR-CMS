package CMSAIML.example.CMSAIML.repository;
import CMSAIML.example.CMSAIML.Entity.FinancialResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FinancialResourceRepository extends JpaRepository<FinancialResource, Long> {
    List<FinancialResource> findByCategoryAndYear(String category, Integer year);
}

