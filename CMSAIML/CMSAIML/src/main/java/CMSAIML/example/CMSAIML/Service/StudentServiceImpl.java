package CMSAIML.example.CMSAIML.Service;

import CMSAIML.example.CMSAIML.Entity.Student;
import CMSAIML.example.CMSAIML.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getStudentsByBranch(String branch) {
        return studentRepository.findByBranch(branch);
    }

    @Override
    public List<Student> getStudentsByCgpaAbove(Double cgpa) {
        return studentRepository.findByCgpaGreaterThan(cgpa);
    }

    @Override
    public List<Student> getStudentsByCgpaBelow(Double cgpa) {
        return studentRepository.findByCgpaLessThan(cgpa);
    }

    @Override
    public List<Student> getStudentsByCgpaRange(Double minCgpa, Double maxCgpa) {
        return studentRepository.findByCgpaBetween(minCgpa, maxCgpa);
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Optional<Student> existingStudent = studentRepository.findById(id);
        if (existingStudent.isPresent()) {
            Student updatedStudent = existingStudent.get();

            // Update fields
            updatedStudent.setName(student.getName());
            updatedStudent.setEmail(student.getEmail());
            updatedStudent.setYear(student.getYear());
            updatedStudent.setCourse(student.getCourse()); // Updated to String
            updatedStudent.setBranch(student.getBranch());
            updatedStudent.setCgpa(student.getCgpa());

            return studentRepository.save(updatedStudent);
        }
        return null;
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
