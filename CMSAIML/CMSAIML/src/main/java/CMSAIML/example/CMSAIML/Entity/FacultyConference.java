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

    private Long id;

    private String facultyName;


    private String conferenceName;

    private String paperTitle;

    private String presentationDate;

    private String conferenceType;

    private String conferenceLocation;


    private String conferenceMode;


    private String publicationStatus;


    private String journalName;

    private String issnNumber;


    private String indexing;


    private String certificateLink;
}
