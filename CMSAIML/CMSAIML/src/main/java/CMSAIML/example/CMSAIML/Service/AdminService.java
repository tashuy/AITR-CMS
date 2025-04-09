package CMSAIML.example.CMSAIML.Service;

import CMSAIML.example.CMSAIML.Entity.Admin;
import CMSAIML.example.CMSAIML.Entity.Student;
import CMSAIML.example.CMSAIML.Entity.Faculty;
import CMSAIML.example.CMSAIML.repository.AdminRepository;
import CMSAIML.example.CMSAIML.repository.StudentRepository;
import CMSAIML.example.CMSAIML.repository.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;


    // -------------------- Admin Authentication --------------------
    public Optional<Admin> authenticateAdmin(String username, String password) {
        return adminRepository.findByUsernameAndPassword(username, password);
    }

    public boolean isAdmin(String username) {
        return adminRepository.findByUsername(username)
                .map(admin -> admin.getUsername().equals("admin"))
                .orElse(false);
    }

    // -------------------- Student Methods --------------------
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student student) {
        student.setId(id);
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    // -------------------- Faculty Methods --------------------
    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    public Faculty getFacultyById(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }


    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty updateFaculty(Long id, Faculty faculty) {
        faculty.setId(id);
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

}