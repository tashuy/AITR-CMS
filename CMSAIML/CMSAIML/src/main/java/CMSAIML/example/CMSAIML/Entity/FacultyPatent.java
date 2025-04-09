package CMSAIML.example.CMSAIML.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class FacultyPatent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String facultyName;
    private String patentTitle;
    private String patentNumber;
    private String applicationDate;
    private String status;
    private String inventorNames;
    private String patentType;
    private String patentOffice;
    private String grantDate;
    private String expiryDate;
    private String country;
    private String patentCategory;
    private String certificateLink;
}
