package CMSAIML.example.CMSAIML.Entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;

@Entity
@Table(name = "faculty_award") // Use lowercase for consistency with SQL
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

    @Lob
    @JdbcTypeCode(SqlTypes.LONGVARBINARY)
    @Column(name = "certificate_pdf")
    private byte[] certificatePdf;
}
