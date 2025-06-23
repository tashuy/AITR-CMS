package CMSAIML.example.CMSAIML.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Data
@Table(name = "faculty_conference")
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

    @Lob
    @JdbcTypeCode(SqlTypes.LONGVARBINARY)
    @Column(name = "certificate_pdf")
    private byte[] certificatePdf;
}
