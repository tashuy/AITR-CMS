package CMSAIML.example.CMSAIML.Entity;

import jakarta.persistence.*;
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

    @Lob
    @Column(name = "certificate_pdf", columnDefinition = "LONGBLOB")
    private byte[] certificatePdf;
}
