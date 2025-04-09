package CMSAIML.example.CMSAIML.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "achievements")
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "institute_department")
    private String instituteDepartment;

    @Column(name = "achievement_title")
    private String achievementTitle;

    @Column(name = "achievement_description", length = 1000)
    private String achievementDescription;

    @Column(name = "achievement_type")
    private String achievementType;

    @Column(name = "date_of_achievement")
    private String dateOfAchievement;

    @Column(name = "certificate_file")
    private String certificateFile;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "spoc_name")
    private String spocName;

    @Column(name = "tag_details")
    private String tagDetails;
}

