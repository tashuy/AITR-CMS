package CMSAIML.example.CMSAIML.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "placements")
public class Placement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;  // Reference to the student placed

    private String company;
    private double packageAmount;  // Salary package offered
    private int year;  // Year of placement

    // Default constructor
    public Placement() {}

    // Parameterized constructor
    public Placement(Long id, Student student, String company, double packageAmount, int year) {
        this.id = id;
        this.student = student;
        this.company = company;
        this.packageAmount = packageAmount;
        this.year = year;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public String getCompany() {
        return company;
    }

    public double getPackageAmount() {
        return packageAmount;
    }

    public int getYear() {
        return year;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setPackageAmount(double packageAmount) {
        this.packageAmount = packageAmount;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
