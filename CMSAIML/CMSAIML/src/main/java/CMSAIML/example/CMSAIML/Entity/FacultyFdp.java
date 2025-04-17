package CMSAIML.example.CMSAIML.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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

    @Lob
    private byte[] certificatePdf;  // Replaces certificateLink with actual PDF binary
}
