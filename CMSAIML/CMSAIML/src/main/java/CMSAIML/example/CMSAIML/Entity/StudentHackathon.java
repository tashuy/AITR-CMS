package CMSAIML.example.CMSAIML.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "student_hackathon")
public class StudentHackathon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;
    private String enrollmentNumber;
    private String eventName;
    private String date;
    private String teamName;
    private int teamSize;
    private String mentorName;
    private String hackathonType;
    private String organizingBody;
    private String venue;
    private String problemStatement;
    private String technologyUsed;
    private BigDecimal prizeMoney;
    private String sponsoringCompany;
    private String position;
    private String projectGithubLink;
    private String projectDescription;
    private String certificateStatus;
    private String certificateLink;
}
