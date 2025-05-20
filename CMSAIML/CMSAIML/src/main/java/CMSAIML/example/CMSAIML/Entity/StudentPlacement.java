package CMSAIML.example.CMSAIML.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class StudentPlacement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;

    private  String enrollmentNumber;
    private String companyName;


    private String jobRole;

    private String branch;

    private String placementType;

    private double packageAmount;

    private String joiningDate;

    private String companyLocation;

    private String interviewMode;

    @Lob
    @Column(length = 16777215)
    private byte[] offerLetterPdf; // PDF stored in DB as BLOB
}
