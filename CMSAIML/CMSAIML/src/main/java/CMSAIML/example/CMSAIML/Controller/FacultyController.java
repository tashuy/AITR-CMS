package CMSAIML.example.CMSAIML.Controller;

import CMSAIML.example.CMSAIML.Entity.Faculty;
import CMSAIML.example.CMSAIML.Service.FacultyService;
import CMSAIML.example.CMSAIML.dto.FacultyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
@CrossOrigin(origins = "http://localhost:5174", allowCredentials = "true")
public class FacultyController {

    private final FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public ResponseEntity<List<Faculty>> getAllFaculty() {
        List<Faculty> facultyList = facultyService.getAllFaculties();
        return ResponseEntity.ok(facultyList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable Long id) {
        Optional<Faculty> faculty = facultyService.getFacultyById(id);
        if (faculty.isPresent()) {
            return ResponseEntity.ok(faculty.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<String> createFaculty(
            @RequestBody FacultyDTO facultyDTO,
            @RequestParam(required = false, defaultValue = "false") boolean isTemporary) {

        facultyService.createFaculty(facultyDTO);
        return ResponseEntity.ok("Faculty added successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable Long id, @RequestBody Faculty faculty) {
        Faculty updatedFaculty = facultyService.updateFaculty(id, faculty);
        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.noContent().build();
    }
}
