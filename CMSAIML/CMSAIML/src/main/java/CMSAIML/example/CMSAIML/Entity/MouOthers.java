package CMSAIML.example.CMSAIML.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "others")
public class MouOthers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "institute_department")
    private String instituteDepartment;

    @Column(name = "title")
    private String title;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "date")
    private String date;


    @Column(name = "spoc_name")
    private String spocName;

    @Column(name = "tag_details")
    private String tagDetails;
}

