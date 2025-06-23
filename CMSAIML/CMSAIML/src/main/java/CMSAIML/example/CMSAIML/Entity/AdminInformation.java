package CMSAIML.example.CMSAIML.Entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminInformation {
    private String fullName;
    private String gender;
    private String dateOfBirth;
    private int age;
    private String bloodGroup;
    private String nationality;
    private String maritalStatus;
    private String contactNumber;
    private String alternateContactNumber;
    private String emailOfficial;
    private String emailPersonal;
    private String residentialAddress;
    private String emergencyContact;
    private String panNumber;
    private String aadhaarNumber;
    private String bankAccountDetails;
}