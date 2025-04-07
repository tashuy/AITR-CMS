package CMSAIML.example.CMSAIML.dto;

import lombok.Data;
@Data
public class FacultyDTO {
    private String name;
    private String email;
    private String department;
    private String mobile_no;  // ✅ Fixed
    private int years_Of_Experience;
    private String designation;

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getYears_Of_Experience() {
        return years_Of_Experience;
    }

    public void setYears_Of_Experience(int years_Of_Experience) {
        this.years_Of_Experience = years_Of_Experience;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }


}
