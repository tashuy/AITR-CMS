package CMSAIML.example.CMSAIML.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class StudentResearchPaper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String enrollement_number;
    private String branch;

    private String student_name;
    private  String award;
    private String title;
    private String journal_name;
    private String publication_date;
    private  String main_author;

    private String co_authors;

}
