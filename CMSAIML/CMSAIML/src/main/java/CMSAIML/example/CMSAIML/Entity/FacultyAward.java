package CMSAIML.example.CMSAIML.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "faculty_award") // Explicit table name
@Data
public class FacultyAward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String faculty_name;


    private String award_name;


    private String awarded_by;


    private LocalDate award_date;


}
