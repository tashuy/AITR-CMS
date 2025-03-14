package CMSAIML.example.CMSAIML.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "faculty_award")
@Data
public class FacultyAward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String facultyName;
    private String awardName;
    private String awardedBy;
    private LocalDate awardDate;
    private String category;
    private String recognitionType;
    private String eventName;
    private String description;
    private String certificateLink;
}
