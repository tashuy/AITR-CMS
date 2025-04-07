package CMSAIML.example.CMSAIML.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "student_internship")
public class StudentInternship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;
    private String enrollmentNumber;
    private String companyName;
    private String role;
    private String internshipType;
    private BigDecimal stipend;
    private String duration;
    private String department;
    private String mentorName;
    private String mentorEmail;
    private String technologiesUsed;
    private String projectName;
    private String projectDescription;
    private String skillsGained;
    private String companyLocation;
    private String internshipStatus;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    private String offerLetterLink;
    private String experienceLetterLink;
    private String certificateLink;
}
