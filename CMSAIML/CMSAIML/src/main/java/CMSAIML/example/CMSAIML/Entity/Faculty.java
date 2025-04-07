package CMSAIML.example.CMSAIML.Entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
@Table(name = "faculty")
@Data
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String department;

    @Column(name = "mobile_no", unique = true)
    private String mobile_no;

    @Column(name = "years_of_experience")
    private Integer years_Of_Experience;

    private String designation;
}
