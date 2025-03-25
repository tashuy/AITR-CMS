package CMSAIML.example.CMSAIML.Entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;
import java.time.LocalDate;

@Entity
@Table(name = "STUDENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("ID")
    private Long id;

    @JsonProperty("NAME")
    @Column(name = "NAME")
    private String name;

    @JsonProperty("EMAIL")
    @Column(name = "EMAIL")
    private String email;

    @JsonProperty("YEAR")
    @Column(name = "YEAR")
    private int year;

    @JsonProperty("COURSE")
    @Column(name = "COURSE")
    private String course;

    @JsonProperty("BRANCH")
    @Column(name = "BRANCH")
    private String branch;

    @JsonProperty("CGPA")
    @Column(name = "CGPA")
    private Double cgpa;

    @JsonProperty("DATE OF BIRTH")
    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @JsonProperty("GENDER")
    @Column(name = "GENDER")
    private String gender;

    @JsonProperty("YEAR OF ADMISSION")
    @Column(name = "YEAR_OF_ADMISSION")
    private int yearOfAdmission;

    @JsonProperty("YEAR OF GRADUATION")
    @Column(name = "YEAR_OF_GRADUATION")
    private int yearOfGraduation;

    @JsonProperty("STATUS")
    @Column(name = "STATUS")
    private String status;

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<Placement> placements;
}
