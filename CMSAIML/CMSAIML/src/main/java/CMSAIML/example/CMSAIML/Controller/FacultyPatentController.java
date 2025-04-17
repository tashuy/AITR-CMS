package CMSAIML.example.CMSAIML.Controller;

import CMSAIML.example.CMSAIML.Entity.FacultyPatent;
import CMSAIML.example.CMSAIML.Service.FacultyPatentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/faculty-patents")
public class FacultyPatentController {

    @Autowired
    private FacultyPatentService facultyPatentService;

    @GetMapping
    public List<FacultyPatent> getAllPatents() {
        return facultyPatentService.getAllPatents();
    }

    @GetMapping("/{id}")
    public FacultyPatent getPatentById(@PathVariable Long id) {
        return facultyPatentService.getPatentById(id);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public FacultyPatent createPatent(
            @RequestParam String facultyName,
            @RequestParam String patentTitle,
            @RequestParam String patentNumber,
            @RequestParam(required = false) String applicationDate,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String inventorNames,
            @RequestParam(required = false) String patentType,
            @RequestParam(required = false) String patentOffice,
            @RequestParam(required = false) String grantDate,
            @RequestParam(required = false) String expiryDate,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String patentCategory,
            @RequestParam(required = false) MultipartFile certificatePdf
    ) throws IOException {
        FacultyPatent patent = new FacultyPatent();
        patent.setFacultyName(facultyName);
        patent.setPatentTitle(patentTitle);
        patent.setPatentNumber(patentNumber);
        patent.setApplicationDate(applicationDate);
        patent.setStatus(status);
        patent.setInventorNames(inventorNames);
        patent.setPatentType(patentType);
        patent.setPatentOffice(patentOffice);
        patent.setGrantDate(grantDate);
        patent.setExpiryDate(expiryDate);
        patent.setCountry(country);
        patent.setPatentCategory(patentCategory);

        if (certificatePdf != null && !certificatePdf.isEmpty()) {
            patent.setCertificatePdf(certificatePdf.getBytes());
        }

        return facultyPatentService.savePatent(patent);
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public FacultyPatent updatePatent(
            @PathVariable Long id,
            @RequestParam String facultyName,
            @RequestParam String patentTitle,
            @RequestParam String patentNumber,
            @RequestParam(required = false) String applicationDate,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String inventorNames,
            @RequestParam(required = false) String patentType,
            @RequestParam(required = false) String patentOffice,
            @RequestParam(required = false) String grantDate,
            @RequestParam(required = false) String expiryDate,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String patentCategory,
            @RequestParam(required = false) MultipartFile certificatePdf
    ) throws IOException {
        FacultyPatent patent = new FacultyPatent();
        patent.setId(id);
        patent.setFacultyName(facultyName);
        patent.setPatentTitle(patentTitle);
        patent.setPatentNumber(patentNumber);
        patent.setApplicationDate(applicationDate);
        patent.setStatus(status);
        patent.setInventorNames(inventorNames);
        patent.setPatentType(patentType);
        patent.setPatentOffice(patentOffice);
        patent.setGrantDate(grantDate);
        patent.setExpiryDate(expiryDate);
        patent.setCountry(country);
        patent.setPatentCategory(patentCategory);

        if (certificatePdf != null && !certificatePdf.isEmpty()) {
            patent.setCertificatePdf(certificatePdf.getBytes());
        }

        return facultyPatentService.savePatent(patent);
    }

    @DeleteMapping("/{id}")
    public void deletePatent(@PathVariable Long id) {
        facultyPatentService.deletePatent(id);
    }
}
