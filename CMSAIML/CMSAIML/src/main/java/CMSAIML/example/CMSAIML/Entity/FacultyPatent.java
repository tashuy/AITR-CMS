package CMSAIML.example.CMSAIML.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Data
public class FacultyPatent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("ID")
    private Long id;

    @JsonProperty("FACULTY NAME")
    private String facultyName;

    @JsonProperty("PATENT TITLE")
    private String patentTitle;

    @JsonProperty("PATENT NUMBER")
    private String patentNumber;

    @JsonProperty("APPLICATION DATE")
    private String applicationDate;

    @JsonProperty("STATUS")
    private String status;

    @JsonProperty("INVENTOR NAMES")
    private String inventorNames;

    @JsonProperty("PATENT TYPE")
    private String patentType;

    @JsonProperty("PATENT OFFICE")
    private String patentOffice;

    @JsonProperty("GRANT DATE")
    private String grantDate;

    @JsonProperty("EXPIRY DATE")
    private String expiryDate;

    @JsonProperty("COUNTRY")
    private String country;

    @JsonProperty("PATENT CATEGORY")
    private String patentCategory;

    @JsonProperty("CERTIFICATE LINK")
    private String certificateLink;
}
