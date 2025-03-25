package CMSAIML.example.CMSAIML.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class FacultyFdp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("ID")
    private Long id;

    @JsonProperty("FACULTY NAME")
    private String facultyName;

    @JsonProperty("PROGRAM NAME")
    private String programName;

    @JsonProperty("ORGANIZED BY")
    private String organizedBy;

    @JsonProperty("START DATE")
    private String startDate;

    @JsonProperty("END DATE")
    private String endDate;

    @JsonProperty("PROGRAM TYPE")
    private String programType;

    @JsonProperty("MODE")
    private String mode;

    @JsonProperty("LOCATION")
    private String location;

    @JsonProperty("DURATION DAYS")
    private int durationDays;

    @JsonProperty("CERTIFICATE LINK")
    private String certificateLink;
}
