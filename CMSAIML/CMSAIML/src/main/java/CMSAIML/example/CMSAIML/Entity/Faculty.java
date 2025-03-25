package CMSAIML.example.CMSAIML.Entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "FACULTY")
@Data
public class Faculty {

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

    @JsonProperty("DEPARTMENT")
    @Column(name = "DEPARTMENT")
    private String department;

    @JsonProperty("MOBILE NUMBER")
    @Column(name = "MOBILE_NO")
    private String mobileNo;

    @JsonProperty("YEARS OF EXPERIENCE")
    @Column(name = "YEARS_OF_EXPERIENCE")
    private int yearsOfExperience;

    @JsonProperty("DESIGNATION")
    @Column(name = "DESIGNATION")
    private String designation;
}
