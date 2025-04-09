package CMSAIML.example.CMSAIML.Controller;

import CMSAIML.example.CMSAIML.Entity.*;
import CMSAIML.example.CMSAIML.Service.*;
import CMSAIML.example.CMSAIML.dto.FacultyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // Admin Login Endpoint
    @PostMapping
    public ResponseEntity<String> authenticateAdmin(@RequestBody Admin admin) {
        Optional<Admin> authenticatedAdmin = adminService.authenticateAdmin(admin.getUsername(), admin.getPassword());
        if (authenticatedAdmin.isPresent()) {
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    // -------------------- Student Endpoints --------------------

    @GetMapping("/student")
    public List<Student> getAllStudents() {
        return adminService.getAllStudents();
    }

    @PostMapping("/student")
    public ResponseEntity<String> addStudent(@RequestParam("course") String course,
                                             @RequestParam("name") String name,
                                             @RequestParam("username") String username) {
        if (adminService.isAdmin(username)) {
            Student student = new Student();
            student.setName(name);
            student.setCourse(course);
            adminService.addStudent(student);
            return ResponseEntity.ok("Student added successfully");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
    }


    @PutMapping("/student/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody Student student, @RequestHeader("username") String username) {
        if (adminService.isAdmin(username)) {
            adminService.updateStudent(id, student);
            return ResponseEntity.ok("Student updated successfully");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id, @RequestHeader("username") String username) {
        if (adminService.isAdmin(username)) {
            adminService.deleteStudent(id);
            return ResponseEntity.ok("Student deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
    }

    // -------------------- Faculty Endpoints --------------------

    @GetMapping("/faculty")
    public List<Faculty> getAllFaculty() {
        return adminService.getAllFaculty();
    }


    @PostMapping("/faculty")
    public ResponseEntity<String> addFaculty(@RequestBody FacultyDTO facultyDTO, @RequestHeader("username") String username) {
        if (adminService.isAdmin(username)) {
            // Create Faculty entity and set properties
            Faculty faculty = new Faculty();
            faculty.setName(facultyDTO.getName());
            faculty.setEmail(facultyDTO.getEmail());
            faculty.setDepartment(facultyDTO.getDepartment());
            faculty.setMobile_no(facultyDTO.getMobile_no());
            faculty.setYears_Of_Experience(facultyDTO.getYears_Of_Experience());
            faculty.setDesignation(facultyDTO.getDesignation());


            // Save Faculty entity
            adminService.addFaculty(faculty);
            return ResponseEntity.ok("Faculty added successfully");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
    }



    @PutMapping("/faculty/{id}")
    public ResponseEntity<String> updateFaculty(@PathVariable Long id, @RequestBody Faculty faculty, @RequestHeader("username") String username) {
        if (adminService.isAdmin(username)) {
            adminService.updateFaculty(id, faculty);
            return ResponseEntity.ok("Faculty updated successfully");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
    }

    @DeleteMapping("/faculty/{id}")
    public ResponseEntity<String> deleteFaculty(@PathVariable Long id, @RequestHeader("username") String username) {
        if (adminService.isAdmin(username)) {
            adminService.deleteFaculty(id);
            return ResponseEntity.ok("Faculty deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");

    }private final FacultyAwardService facultyAwardService;
    @GetMapping("/faculty-awards")
    public List<FacultyAward> getAllAwards() {
        return facultyAwardService.getAllAwards();
    }

    @PostMapping("/faculty-awards")
    public FacultyAward addAward(@RequestBody FacultyAward award) {
        return facultyAwardService.saveAward(award);
    }

    @PutMapping("/faculty-awards/{id}")
    public FacultyAward updateAward(@PathVariable Long id, @RequestBody FacultyAward award) {
        return facultyAwardService.updateAward(id, award);
    }

    @DeleteMapping("/faculty-awards/{id}")
    public void deleteAward(@PathVariable Long id) {
        facultyAwardService.deleteAward(id);
    }
    private final FacultyFdpService facultyFdpService;

// -------------------- Faculty FDP Endpoints --------------------
// FDP (Faculty Development Program) endpoints
@GetMapping("/faculty-fdp")
public List<FacultyFdp> getAllFdp() {
    return facultyFdpService.getAllFdps();
}

    @PostMapping("/faculty-fdp")
    public FacultyFdp addFdp(@RequestBody FacultyFdp fdp) {
        return facultyFdpService.saveFdp(fdp);
    }

    @PutMapping("/faculty-fdp/{id}")
    public FacultyFdp updateFdp(@PathVariable Long id, @RequestBody FacultyFdp fdp) {
        return facultyFdpService.updateFdp(id, fdp);
    }

    @DeleteMapping("/faculty-fdp/{id}")
    public void deleteFdp(@PathVariable Long id) {
        facultyFdpService.deleteFdp(id);
    }

    private final StudentSportsService studentSportsService;
    @PostMapping("/student-sports")
    public ResponseEntity<StudentSports> uploadStudentSports(
            @RequestPart("sports") StudentSports sports,
            @RequestPart(value = "certificate", required = false) MultipartFile certificate) throws IOException {

        if (certificate != null && !certificate.isEmpty()) {
            sports.setCertificate(certificate.getBytes());
        }

        return ResponseEntity.ok(studentSportsService.saveSports(sports));
    }

    @GetMapping("/student-sports/{id}/certificate")
    public ResponseEntity<byte[]> downloadCertificate(@PathVariable Long id) {
        StudentSports sports = studentSportsService.getSportsById(id);
        if (sports != null && sports.getCertificate() != null) {
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=certificate.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(sports.getCertificate());
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/student-sports")
    public List<StudentSports> getAllSports() {
        return studentSportsService.getAllSports();
    }

    @PutMapping("/student-sports/{id}")
    public StudentSports updateStudentSports(@PathVariable Long id, @RequestBody StudentSports sports) {
        sports.setId(id);
        return studentSportsService.saveSports(sports);
    }

    @DeleteMapping("/student-sports/{id}")
    public void deleteStudentSports(@PathVariable Long id) {
        studentSportsService.deleteSports(id);
    }

    private final StudentPlacementService studentPlacementService;
    @PostMapping("/student-placement")
    public ResponseEntity<StudentPlacement> uploadStudentPlacement(
            @RequestPart("placement") StudentPlacement placement,
            @RequestPart(value = "offerLetter", required = false) MultipartFile offerLetter) throws IOException {

        if (offerLetter != null && !offerLetter.isEmpty()) {
            placement.setOfferLetterPdf(offerLetter.getBytes());
        }

        return ResponseEntity.ok(studentPlacementService.createPlacement(placement));
    }


    // -------------------- Event Endpoints --------------------

}