package CMSAIML.example.CMSAIML.Entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

@Entity
@Table(name = "FACULTY_AWARD")
@Data
public class FacultyAward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("ID")
    private Long id;

    @JsonProperty("FACULTY NAME")
    private String facultyName;

    @JsonProperty("AWARD NAME")
    private String awardName;

    @JsonProperty("AWARDED BY")
    private String awardedBy;

    @JsonProperty("AWARD DATE")
    private LocalDate awardDate;

    @JsonProperty("CATEGORY")
    private String category;

    @JsonProperty("RECOGNITION TYPE")
    private String recognitionType;

    @JsonProperty("EVENT NAME")
    private String eventName;

    @JsonProperty("DESCRIPTION")
    private String description;

    @JsonProperty("CERTIFICATE LINK")
    private String certificateLink;
}
