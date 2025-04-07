package CMSAIML.example.CMSAIML.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "student_certificate")
public class StudentCertificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;

    private String enrollmentNumber;

    private String certificateName;

    private String certificateType;

    private String issuedBy;

    private String issueDate;

    private String validityPeriod;

    private String gradeOrScore;

    private String certificateDescription;

    private String modeOfTraining;

    private String relatedCourseOrProgram;

    private String certificateStatus;

    private Integer verified;

    private String certificateLink;
}
