package CMSAIML.example.CMSAIML.Entity;
import jakarta.persistence.*; // For JPA annotations
import lombok.*;
@Entity
@Table(name = "financial_resources")
public class FinancialResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category; // Engineering / Overall
    private Double operationalExpenses;
    private Double capitalExpenses;
    private Double salaryExpenditure;
    private Integer year;
}
