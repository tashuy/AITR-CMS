package CMSAIML.example.CMSAIML.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Data
public class StudentSports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("ID")
    private Long id;

    @JsonProperty("STUDENT NAME")
    private String studentName;

    @JsonProperty("SPORT NAME")
    private String sportName;

    @JsonProperty("ACHIEVEMENT")
    private String achievement;

    @JsonProperty("EVENT DATE")
    private String eventDate;

    @JsonProperty("EVENT NAME")
    private String eventName;

    @JsonProperty("EVENT LEVEL")
    private String eventLevel;

    @JsonProperty("EVENT LOCATION")
    private String eventLocation;

    @JsonProperty("POSITION")
    private String position;

    @JsonProperty("CERTIFICATE LINK")
    private String certificateLink;

    @JsonProperty("COACH NAME")
    private String coachName;
}
