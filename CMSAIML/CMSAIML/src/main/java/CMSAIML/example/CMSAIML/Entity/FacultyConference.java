package CMSAIML.example.CMSAIML.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Data
public class FacultyConference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("ID")
    private Long id;

    @JsonProperty("FACULTY NAME")
    private String facultyName;

    @JsonProperty("CONFERENCE NAME")
    private String conferenceName;

    @JsonProperty("PAPER TITLE")
    private String paperTitle;

    @JsonProperty("PRESENTATION DATE")
    private String presentationDate;

    @JsonProperty("CONFERENCE TYPE")
    private String conferenceType;

    @JsonProperty("CONFERENCE LOCATION")
    private String conferenceLocation;

    @JsonProperty("CONFERENCE MODE")
    private String conferenceMode;

    @JsonProperty("PUBLICATION STATUS")
    private String publicationStatus;

    @JsonProperty("JOURNAL NAME")
    private String journalName;

    @JsonProperty("ISSN NUMBER")
    private String issnNumber;

    @JsonProperty("INDEXING")
    private String indexing;

    @JsonProperty("CERTIFICATE LINK")
    private String certificateLink;
}
