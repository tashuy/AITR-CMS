package CMSAIML.example.CMSAIML.Controller;

import CMSAIML.example.CMSAIML.Entity.FacultyAward;
import CMSAIML.example.CMSAIML.Service.FacultyAwardService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/faculty-awards")
public class FacultyAwardController {

    private final FacultyAwardService facultyAwardService;

    public FacultyAwardController(FacultyAwardService facultyAwardService) {
        this.facultyAwardService = facultyAwardService;
    }

    @GetMapping
    public ResponseEntity<List<FacultyAward>> getAllAwards() {
        return ResponseEntity.ok(facultyAwardService.getAllAwards());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyAward> getAwardById(@PathVariable Long id) {
        FacultyAward award = facultyAwardService.getAwardById(id);
        return award != null ? ResponseEntity.ok(award) : ResponseEntity.notFound().build();
    }

    // ✅ Create with file upload
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createAward(
            @RequestParam String facultyName,
            @RequestParam String awardName,
            @RequestParam(required = false) String awardedBy,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate awardDate,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String recognitionType,
            @RequestParam(required = false) String eventName,
            @RequestParam(required = false) String description,
            @RequestParam("certificatePdf") MultipartFile certificatePdf
    ) {
        try {
            facultyAwardService.saveAward(
                    facultyName, awardName, awardedBy, awardDate,
                    category, recognitionType, eventName, description, certificatePdf
            );
            return ResponseEntity.ok("Award saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    // ✅ Download certificate
    @GetMapping("/{id}/certificate")
    public ResponseEntity<byte[]> downloadCertificate(@PathVariable Long id) {
        FacultyAward award = facultyAwardService.getAwardById(id);
        if (award != null && award.getCertificatePdf() != null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=certificate.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(award.getCertificatePdf());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAward(@PathVariable Long id) {
        facultyAwardService.deleteAward(id);
        return ResponseEntity.noContent().build();
    }
}
