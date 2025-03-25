package CMSAIML.example.CMSAIML.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("STUDENT NAME")
    private String studentName;

    @JsonProperty("ENROLLMENT NUMBER")
    private String enrollmentNumber;

    @JsonProperty("EVENT NAME")
    private String eventName;

    @JsonProperty("DATE")
    private String date;

    @JsonProperty("TEAM NAME")
    private String teamName;

    @JsonProperty("TEAM SIZE")
    private int teamSize;

    @JsonProperty("MENTOR NAME")
    private String mentorName;

    @JsonProperty("HACKATHON TYPE")
    private String hackathonType;

    @JsonProperty("ORGANIZING BODY")
    private String organizingBody;

    @JsonProperty("VENUE")
    private String venue;

    @JsonProperty("PROBLEM STATEMENT")
    private String problemStatement;

    @JsonProperty("TECHNOLOGY USED")
    private String technologyUsed;

    @JsonProperty("PRIZE MONEY")
    private BigDecimal prizeMoney;

    @JsonProperty("SPONSORING COMPANY")
    private String sponsoringCompany;

    @JsonProperty("POSITION")
    private String position;

    @JsonProperty("PROJECT GITHUB LINK")
    private String projectGithubLink;

    @JsonProperty("PROJECT DESCRIPTION")
    private String projectDescription;

    @JsonProperty("CERTIFICATE STATUS")
    private String certificateStatus;

    @JsonProperty("CERTIFICATE LINK")
    private String certificateLink;
}
