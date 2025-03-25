package CMSAIML.example.CMSAIML.Entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "student_internship")
public class StudentInternship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("ID")
    @Column(name = "ID")
    private Long id;

    @JsonProperty("STUDENT NAME")
    @Column(name = "STUDENT_NAME")
    private String studentName;

    @JsonProperty("ENROLLMENT NUMBER")
    @Column(name = "ENROLLMENT_NUMBER")
    private String enrollmentNumber;

    @JsonProperty("COMPANY NAME")
    @Column(name = "COMPANY_NAME")
    private String companyName;

    @JsonProperty("ROLE")
    @Column(name = "ROLE")
    private String role;

    @JsonProperty("INTERNSHIP TYPE")
    @Column(name = "INTERNSHIP_TYPE")
    private String internshipType;

    @JsonProperty("STIPEND")
    @Column(name = "STIPEND")
    private BigDecimal stipend;

    @JsonProperty("DURATION")
    @Column(name = "DURATION")
    private String duration;

    @JsonProperty("DEPARTMENT")
    @Column(name = "DEPARTMENT")
    private String department;

    @JsonProperty("MENTOR NAME")
    @Column(name = "MENTOR_NAME")
    private String mentorName;

    @JsonProperty("MENTOR EMAIL")
    @Column(name = "MENTOR_EMAIL")
    private String mentorEmail;

    @JsonProperty("TECHNOLOGIES USED")
    @Column(name = "technologies_used") // Keeping lowercase as per DB schema
    private String technologiesUsed;

    @JsonProperty("PROJECT NAME")
    @Column(name = "PROJECT_NAME")
    private String projectName;

    @JsonProperty("PROJECT DESCRIPTION")
    @Column(name = "project_description") // Keeping lowercase as per DB schema
    private String projectDescription;

    @JsonProperty("SKILLS GAINED")
    @Column(name = "skills_gained") // Keeping lowercase as per DB schema
    private String skillsGained;

    @JsonProperty("COMPANY LOCATION")
    @Column(name = "COMPANY_LOCATION")
    private String companyLocation;

    @JsonProperty("INTERNSHIP STATUS")
    @Column(name = "INTERNSHIP_STATUS")
    private String internshipStatus;

    @JsonProperty("START DATE")
    @Temporal(TemporalType.DATE)
    @Column(name = "START_DATE")
    private Date startDate;

    @JsonProperty("END DATE")
    @Temporal(TemporalType.DATE)
    @Column(name = "END_DATE")
    private Date endDate;

    @JsonProperty("OFFER LETTER LINK")
    @Column(name = "OFFER_LETTER_LINK")
    private String offerLetterLink;

    @JsonProperty("EXPERIENCE LETTER LINK")
    @Column(name = "EXPERIENCE_LETTER_LINK")
    private String experienceLetterLink;

    @JsonProperty("CERTIFICATE LINK")
    @Column(name = "CERTIFICATE_LINK")
    private String certificateLink;
}
