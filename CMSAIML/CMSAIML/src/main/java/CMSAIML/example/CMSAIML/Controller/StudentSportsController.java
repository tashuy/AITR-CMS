package CMSAIML.example.CMSAIML.Controller;

import CMSAIML.example.CMSAIML.Entity.StudentSports;
import CMSAIML.example.CMSAIML.Service.StudentSportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/sports")
public class StudentSportsController {

    @Autowired
    private StudentSportsService studentSportsService;

    @GetMapping
    public List<StudentSports> getAllSports() {
        return studentSportsService.getAllSports();
    }

    @GetMapping("/{id}")
    public StudentSports getSportsById(@PathVariable Long id) {
        return studentSportsService.getSportsById(id);
    }

    // ✅ Upload sports entry with PDF
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StudentSports> addSports(
            @RequestParam String studentName,
            @RequestParam String sportName,
            @RequestParam(required = false) String achievement,
            @RequestParam(required = false) String eventDate,
            @RequestParam(required = false) String eventName,
            @RequestParam(required = false) String eventLevel,
            @RequestParam(required = false) String eventLocation,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String coachName,
            @RequestParam(required = false) MultipartFile certificate
    ) throws IOException {
        StudentSports sports = new StudentSports();
        sports.setStudentName(studentName);
        sports.setSportName(sportName);
        sports.setAchievement(achievement);
        sports.setEventDate(eventDate);
        sports.setEventName(eventName);
        sports.setEventLevel(eventLevel);
        sports.setEventLocation(eventLocation);
        sports.setPosition(position);
        sports.setCoachName(coachName);

        if (certificate != null && !certificate.isEmpty()) {
            sports.setCertificate(certificate.getBytes());
        }

        return ResponseEntity.ok(studentSportsService.saveSports(sports));
    }

    // ✅ Download/view certificate
    @GetMapping("/{id}/certificate")
    public ResponseEntity<byte[]> getCertificate(@PathVariable Long id) {
        StudentSports sports = studentSportsService.getSportsById(id);
        if (sports == null || sports.getCertificate() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=certificate.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(sports.getCertificate());
    }

    @PutMapping("/{id}")
    public StudentSports updateSports(@PathVariable Long id, @RequestBody StudentSports sports) {
        sports.setId(id);
        return studentSportsService.saveSports(sports);
    }

    @DeleteMapping("/{id}")
    public void deleteSports(@PathVariable Long id) {
        studentSportsService.deleteSports(id);
    }
}
