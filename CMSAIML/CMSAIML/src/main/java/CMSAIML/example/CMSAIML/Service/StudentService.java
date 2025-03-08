package CMSAIML.example.CMSAIML.Service;

import CMSAIML.example.CMSAIML.Entity.Student;
import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<Student> getAllStudents();  // Fetch all students

    Optional<Student> getStudentById(Long id);  // Fetch student by ID

    Student createStudent(Student student);  // Create a new student

    Student updateStudent(Long id, Student student);  // Update an existing student

    void deleteStudent(Long id);  // Delete student by ID

    // New Methods for CGPA and Branch Classification
    List<Student> getStudentsByBranch(String branch);  // Fetch students by branch

    List<Student> getStudentsByCgpaAbove(Double cgpa);  // Fetch students with CGPA above a value

    List<Student> getStudentsByCgpaBelow(Double cgpa);  // Fetch students with CGPA below a value

    List<Student> getStudentsByCgpaRange(Double minCgpa, Double maxCgpa);  // Fetch students within a CGPA range
}
