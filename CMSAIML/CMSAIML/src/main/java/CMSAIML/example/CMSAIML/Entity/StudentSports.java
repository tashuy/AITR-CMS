package CMSAIML.example.CMSAIML.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class StudentSports {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;
    private String sportName;
    private String achievement;
    private String eventDate;
    private String eventName;
    private String eventLevel;
    private String eventLocation;
    private String position;

    @Lob
    @Column(length = 16777215)
    private byte[] certificate; // stores the PDF as byte array

    private String coachName;
}
