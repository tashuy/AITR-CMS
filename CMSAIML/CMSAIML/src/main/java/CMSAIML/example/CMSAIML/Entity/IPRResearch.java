package CMSAIML.example.CMSAIML.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ipr_research")
@Data
public class IPRResearch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Project title is required")
    @Column(name = "project_title", nullable = false, length = 255)
    private String projectTitle;

    @NotBlank(message = "Funding agency is required")
    @Column(name = "funding_agency", nullable = false, length = 255)
    private String fundingAgency;

    @NotNull(message = "Amount funded is required")
    @DecimalMin(value = "0.01", message = "Amount funded must be greater than zero")
    @Column(name = "amount_funded", precision = 15, scale = 2, nullable = false)
    private BigDecimal amountFunded;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ResearchStatus status;

    @NotBlank(message = "Principal investigator is required")
    @Column(name = "principal_investigator", nullable = false, length = 255)
    private String principalInvestigator;

    @NotNull(message = "Start date is required")
    @Temporal(TemporalType.DATE)
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    private Date endDate;
}
