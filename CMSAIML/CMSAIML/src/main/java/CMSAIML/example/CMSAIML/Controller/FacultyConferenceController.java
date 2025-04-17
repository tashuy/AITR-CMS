package CMSAIML.example.CMSAIML.Controller;

import CMSAIML.example.CMSAIML.Entity.FacultyConference;
import CMSAIML.example.CMSAIML.Service.FacultyConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/faculty-conferences")
public class FacultyConferenceController {

    @Autowired
    private FacultyConferenceService facultyConferenceService;

    @GetMapping
    public List<FacultyConference> getAllConferences() {
        return facultyConferenceService.getAllConferences();
    }

    @GetMapping("/{id}")
    public FacultyConference getConferenceById(@PathVariable Long id) {
        return facultyConferenceService.getConferenceById(id);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FacultyConference> createConference(
            @RequestPart("data") FacultyConference conference,
            @RequestPart("pdf") MultipartFile file
    ) throws IOException {
        conference.setCertificatePdf(file.getBytes());
        FacultyConference saved = facultyConferenceService.saveConference(conference);
        return ResponseEntity.ok(saved);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FacultyConference> updateConference(
            @PathVariable Long id,
            @RequestPart("data") FacultyConference conference,
            @RequestPart("pdf") MultipartFile file
    ) throws IOException {
        conference.setId(id);
        conference.setCertificatePdf(file.getBytes());
        FacultyConference updated = facultyConferenceService.saveConference(conference);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}/download-pdf")
    public ResponseEntity<byte[]> downloadCertificatePdf(@PathVariable Long id) {
        FacultyConference conference = facultyConferenceService.getConferenceById(id);
        if (conference == null || conference.getCertificatePdf() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=certificate_" + id + ".pdf")
                .body(conference.getCertificatePdf());
    }

    @DeleteMapping("/{id}")
    public void deleteConference(@PathVariable Long id) {
        facultyConferenceService.deleteConference(id);
    }
}
