package CMSAIML.example.CMSAIML.repository;
import CMSAIML.example.CMSAIML.Entity.IPRResearch;
import CMSAIML.example.CMSAIML.Entity.ResearchStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.util.List;

public interface IPRResearchRepository extends JpaRepository<IPRResearch, Long> {

    // Find projects by funding agency dynamically with pagination
    List<IPRResearch> findByFundingAgency(String fundingAgency);

    // Find ongoing or completed projects
    List<IPRResearch> findByStatus(ResearchStatus status);

    // Find projects with funding greater than a specified amount using a custom query
    @Query("SELECT i FROM IPRResearch i WHERE i.amountFunded > :amount")
    List<IPRResearch> findProjectsWithFundingGreaterThan(@Param("amount") BigDecimal amount);

    // Find projects with funding greater than a specified amount with pagination and sorting
    @Query("SELECT i FROM IPRResearch i WHERE i.amountFunded > :amount")
    Page<IPRResearch> findProjectsWithFundingGreaterThanPaginated(@Param("amount") BigDecimal amount, Pageable pageable);

    // Find projects with a specific funding agency and status (using dynamic queries)
    List<IPRResearch> findByFundingAgencyAndStatus(String fundingAgency, ResearchStatus status);
}
