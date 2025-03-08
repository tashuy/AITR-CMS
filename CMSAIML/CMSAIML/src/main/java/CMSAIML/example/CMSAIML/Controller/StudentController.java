package CMSAIML.example.CMSAIML.Controller;

import CMSAIML.example.CMSAIML.Entity.Student;
import CMSAIML.example.CMSAIML.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return ResponseEntity.of(studentService.getStudentById(id));
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        Student updatedStudent = studentService.updateStudent(id, studentDetails);
        return ResponseEntity.ok(updatedStudent);  // Return updated student with 200 OK status
    }
    // Fetch students by branch
    @GetMapping("/branch/{branch}")
    public List<Student> getStudentsByBranch(@PathVariable String branch) {
        return studentService.getStudentsByBranch(branch);
    }

    // Fetch students with CGPA above a value
    @GetMapping("/cgpa/above/{cgpa}")
    public List<Student> getStudentsByCgpaAbove(@PathVariable Double cgpa) {
        return studentService.getStudentsByCgpaAbove(cgpa);
    }

    // Fetch students with CGPA below a value
    @GetMapping("/cgpa/below/{cgpa}")
    public List<Student> getStudentsByCgpaBelow(@PathVariable Double cgpa) {
        return studentService.getStudentsByCgpaBelow(cgpa);
    }

    // Fetch students within a CGPA range
    @GetMapping("/cgpa/range")
    public List<Student> getStudentsByCgpaRange(
            @RequestParam Double minCgpa,
            @RequestParam Double maxCgpa) {
        return studentService.getStudentsByCgpaRange(minCgpa, maxCgpa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        try {
            studentService.deleteStudent(id);  // Call service to delete student
            return ResponseEntity.noContent().build();  // Return 204 No Content if successful
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();  // Return 404 if student not found
        }
    }
}
