package CMSAIML.example.CMSAIML.Service;

import CMSAIML.example.CMSAIML.Entity.Faculty;
import CMSAIML.example.CMSAIML.dto.FacultyDTO;

import java.util.List;
import java.util.Optional;

public interface FacultyService {

    List<Faculty> getAllFaculties();  // Fetch all faculties

    Optional<Faculty> getFacultyById(Long id);  // Fetch faculty by ID

    void createFaculty(FacultyDTO faculty);  // Create a new faculty (Removed departmentId)

    Faculty updateFaculty(Long id, Faculty faculty);  // Update an existing faculty

    void deleteFaculty(Long id);  // Delete faculty by ID
}
