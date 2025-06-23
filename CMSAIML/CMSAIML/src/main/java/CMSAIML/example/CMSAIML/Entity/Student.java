package CMSAIML.example.CMSAIML.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class    Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private int year;
    private String course;
    private String branch;
    private Double cgpa;
    private LocalDate dateOfBirth;
    private String gender;
    private int yearOfAdmission;
    private int yearOfGraduation;
    private String status;
}
