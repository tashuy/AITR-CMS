package CMSAIML.example.CMSAIML.Controller;

import CMSAIML.example.CMSAIML.Entity.FacultyFdp;
import CMSAIML.example.CMSAIML.Service.FacultyFdpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/faculty-fdps")
public class FacultyFdpController {

    @Autowired
    private FacultyFdpService facultyFdpService;

    @GetMapping
    public List<FacultyFdp> getAllFdps() {
        return facultyFdpService.getAllFdps();
    }

    @GetMapping("/{id}")
    public FacultyFdp getFdpById(@PathVariable Long id) {
        return facultyFdpService.getFdpById(id);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FacultyFdp createFdp(@RequestPart("fdp") FacultyFdp fdp,
                                @RequestPart("certificatePdf") MultipartFile file) throws Exception {
        fdp.setCertificatePdf(file.getBytes());
        return facultyFdpService.saveFdp(fdp);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FacultyFdp updateFdp(@PathVariable Long id,
                                @RequestPart("fdp") FacultyFdp fdp,
                                @RequestPart("certificatePdf") MultipartFile file) throws Exception {
        fdp.setId(id);
        fdp.setCertificatePdf(file.getBytes());
        return facultyFdpService.saveFdp(fdp);
    }

    @GetMapping("/{id}/certificate")
    public ResponseEntity<byte[]> downloadCertificate(@PathVariable Long id) {
        FacultyFdp fdp = facultyFdpService.getFdpById(id);
        byte[] pdfData = fdp.getCertificatePdf();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=certificate_" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfData);
    }

    @DeleteMapping("/{id}")
    public void deleteFdp(@PathVariable Long id) {
        facultyFdpService.deleteFdp(id);
    }
}
