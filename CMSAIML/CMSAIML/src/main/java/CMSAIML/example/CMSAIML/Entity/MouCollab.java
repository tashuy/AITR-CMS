package CMSAIML.example.CMSAIML.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "mou_collaborations")
public class MouCollab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Acts as Sno.

    @Column(name = "institute_department")
    private String instituteDepartment;

    @Column(name = "organization_name")
    private String organizationName;

    @Column(name = "representative_designation")
    private String representativeDesignation;

    @Column(name = "date_of_agreement")
    private String dateOfAgreement;

    private String duration;

    @Column(name = "organization_logo")
    private String organizationLogo;

    @Column(name = "collaboration_type")
    private String collaborationType;

    @Column(name = "event_details")
    private String eventDetails;

    @Column(name = "spoc_name")
    private String spocName;

    @Column(name = "tag_details")
    private String tagDetails;
}

