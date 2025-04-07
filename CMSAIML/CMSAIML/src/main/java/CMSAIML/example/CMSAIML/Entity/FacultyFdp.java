package CMSAIML.example.CMSAIML.Entity;

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
    private Long id;

    private String facultyName;

    private String programName;

    private String organizedBy;

    private String startDate;

    private String endDate;

    private String programType;

    private String mode;

    private String location;

    private int durationDays;

    private String certificateLink;
}
