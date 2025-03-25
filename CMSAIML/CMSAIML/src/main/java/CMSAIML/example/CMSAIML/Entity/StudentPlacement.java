package CMSAIML.example.CMSAIML.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Data
public class StudentPlacement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("ID")
    private Long id;

    @JsonProperty("STUDENT NAME")
    private String studentName;

    @JsonProperty("COMPANY NAME")
    private String companyName;

    @JsonProperty("JOB ROLE")
    private String jobRole;

    @JsonProperty("BRANCH")
    private String branch;

    @JsonProperty("PLACEMENT TYPE")
    private String placementType;

    @JsonProperty("PACKAGE AMOUNT")
    private double packageAmount;

    @JsonProperty("JOINING DATE")
    private String joiningDate;

    @JsonProperty("OFFER LETTER LINK")
    private String offerLetterLink;

    @JsonProperty("COMPANY LOCATION")
    private String companyLocation;

    @JsonProperty("INTERVIEW MODE")
    private String interviewMode;
}
