package CMSAIML.example.CMSAIML.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StudentDTO {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Course is required")
    private String course;
}
