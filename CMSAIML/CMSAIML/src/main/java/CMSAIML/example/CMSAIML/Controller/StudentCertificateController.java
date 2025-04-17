package CMSAIML.example.CMSAIML.Controller;

import CMSAIML.example.CMSAIML.Entity.StudentCertificate;
import CMSAIML.example.CMSAIML.Service.StudentCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/certificates")
public class StudentCertificateController {

    @Autowired
    private StudentCertificateService studentCertificateService;

    @GetMapping
    public List<StudentCertificate> getAllCertificates() {
        return studentCertificateService.getAllCertificates();
    }

    @GetMapping("/{id}")
    public StudentCertificate getCertificateById(@PathVariable Long id) {
        return studentCertificateService.getCertificateById(id);
    }

    @PostMapping
    public StudentCertificate addCertificate(
            @RequestPart("certificate") StudentCertificate certificate,
            @RequestPart("pdf") MultipartFile pdfFile
    ) throws IOException {
        certificate.setCertificatePdf(pdfFile.getBytes());
        return studentCertificateService.saveCertificate(certificate);
    }

    @PutMapping("/{id}")
    public StudentCertificate updateCertificate(
            @PathVariable Long id,
            @RequestPart("certificate") StudentCertificate certificate,
            @RequestPart("pdf") MultipartFile pdfFile
    ) throws IOException {
        certificate.setId(id);
        certificate.setCertificatePdf(pdfFile.getBytes());
        return studentCertificateService.saveCertificate(certificate);
    }

    @DeleteMapping("/{id}")
    public void deleteCertificate(@PathVariable Long id) {
        studentCertificateService.deleteCertificate(id);
    }
}
