package CMSAIML.example.CMSAIML.Entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Data
@Table(name = "student_certificate")
public class StudentCertificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("ID")
    private Long id;

    @JsonProperty("STUDENT NAME")
    private String studentName;

    @JsonProperty("ENROLLMENT NUMBER")
    private String enrollmentNumber;

    @JsonProperty("CERTIFICATE NAME")
    private String certificateName;

    @JsonProperty("CERTIFICATE TYPE")
    private String certificateType;

    @JsonProperty("ISSUED BY")
    private String issuedBy;

    @JsonProperty("ISSUE DATE")
    private String issueDate;

    @JsonProperty("VALIDITY PERIOD")
    private String validityPeriod;

    @JsonProperty("GRADE OR SCORE")
    private String gradeOrScore;

    @JsonProperty("CERTIFICATE DESCRIPTION")
    private String certificateDescription;

    @JsonProperty("MODE OF TRAINING")
    private String modeOfTraining;

    @JsonProperty("RELATED COURSE OR PROGRAM")
    private String relatedCourseOrProgram;

    @JsonProperty("CERTIFICATE STATUS")
    private String certificateStatus;

    @JsonProperty("VERIFIED")
    private Integer verified;

    @JsonProperty("CERTIFICATE LINK")
    private String certificateLink;
}
