package CMSAIML.example.CMSAIML.Controller;

import CMSAIML.example.CMSAIML.Entity.StudentInternship;
import CMSAIML.example.CMSAIML.Service.StudentInternshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/internships")
public class StudentInternshipController {

    @Autowired
    private StudentInternshipService studentInternshipService;

    @GetMapping
    public List<StudentInternship> getAllInternships() {
        return studentInternshipService.getAllInternships();
    }

    @GetMapping("/{id}")
    public StudentInternship getInternshipById(@PathVariable Long id) {
        return studentInternshipService.getInternshipById(id);
    }

    @PostMapping(consumes = "multipart/form-data")
    public StudentInternship addInternship(
            @RequestPart("internship") StudentInternship internship,
            @RequestPart(value = "offerLetterPdf", required = false) MultipartFile offerLetterPdf,
            @RequestPart(value = "experienceLetterPdf", required = false) MultipartFile experienceLetterPdf,
            @RequestPart(value = "certificatePdf", required = false) MultipartFile certificatePdf
    ) throws IOException {

        if (offerLetterPdf != null) internship.setOfferLetterPdf(offerLetterPdf.getBytes());
        if (experienceLetterPdf != null) internship.setExperienceLetterPdf(experienceLetterPdf.getBytes());
        if (certificatePdf != null) internship.setCertificatePdf(certificatePdf.getBytes());

        return studentInternshipService.saveInternship(internship);
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public StudentInternship updateInternship(
            @PathVariable Long id,
            @RequestPart("internship") StudentInternship internship,
            @RequestPart(value = "offerLetterPdf", required = false) MultipartFile offerLetterPdf,
            @RequestPart(value = "experienceLetterPdf", required = false) MultipartFile experienceLetterPdf,
            @RequestPart(value = "certificatePdf", required = false) MultipartFile certificatePdf
    ) throws IOException {

        internship.setId(id);
        if (offerLetterPdf != null) internship.setOfferLetterPdf(offerLetterPdf.getBytes());
        if (experienceLetterPdf != null) internship.setExperienceLetterPdf(experienceLetterPdf.getBytes());
        if (certificatePdf != null) internship.setCertificatePdf(certificatePdf.getBytes());

        return studentInternshipService.saveInternship(internship);
    }

    @DeleteMapping("/{id}")
    public void deleteInternship(@PathVariable Long id) {
        studentInternshipService.deleteInternship(id);
    }
}
