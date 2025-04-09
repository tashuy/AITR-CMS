package CMSAIML.example.CMSAIML.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "events")
public class MouEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Acts as serial number

    @Column(name = "institute_department")
    private String instituteDepartment;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "event_description", length = 1000)
    private String eventDescription;

    @Column(name = "event_type")
    private String eventType; // e.g., Workshop, Seminar, Training, etc.

    @Column(name = "date_of_event")
    private String dateOfEvent;

    @Column(name = "organizer_name")
    private String organizerName;


    @Column(name = "spoc_name")
    private String spocName;

    @Column(name = "tag_details")
    private String tagDetails;
}

