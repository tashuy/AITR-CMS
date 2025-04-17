package CMSAIML.example.CMSAIML.Controller;

import CMSAIML.example.CMSAIML.Entity.StudentPlacement;
import CMSAIML.example.CMSAIML.Service.StudentPlacementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;
import java.io.IOException;

@RestController
@RequestMapping("/student-placements")
public class StudentPlacementController {

    @Autowired
    private StudentPlacementService studentPlacementService;

    @GetMapping
    public List<StudentPlacement> getAllPlacements() {
        return studentPlacementService.getAllPlacements();
    }

    @GetMapping("/{id}")
    public StudentPlacement getPlacementById(@PathVariable Long id) {
        return studentPlacementService.getPlacementById(id);
    }

    // Create with PDF upload
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public StudentPlacement createPlacement(
            @RequestPart("placement") StudentPlacement placement,
            @RequestPart("file") MultipartFile file) throws IOException {

        placement.setOfferLetterPdf(file.getBytes());
        return studentPlacementService.createPlacement(placement);
    }

    // Download offer letter PDF
    @GetMapping("/{id}/offer-letter")
    public ResponseEntity<byte[]> downloadOfferLetter(@PathVariable Long id) {
        byte[] pdfData = studentPlacementService.getOfferLetterPdf(id);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"offer_letter.pdf\"")
                .body(pdfData);
    }

    @PutMapping("/{id}")
    public StudentPlacement updatePlacement(@PathVariable Long id, @RequestBody StudentPlacement placement) {
        placement.setId(id);
        return studentPlacementService.updatePlacement(id, placement);
    }

    @DeleteMapping("/{id}")
    public void deletePlacement(@PathVariable Long id) {
        studentPlacementService.deletePlacement(id);
    }
}
