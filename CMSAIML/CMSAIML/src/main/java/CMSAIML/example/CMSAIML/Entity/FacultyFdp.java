package CMSAIML.example.CMSAIML.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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
    @JdbcTypeCode(SqlTypes.LONGVARBINARY)
    private byte[] certificatePdf;  // Replaces certificateLink with actual PDF binary
}
