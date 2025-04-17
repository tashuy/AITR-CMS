package CMSAIML.example.CMSAIML.Service;

import CMSAIML.example.CMSAIML.Entity.FacultyPatent;
import CMSAIML.example.CMSAIML.repository.FacultyPatentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FacultyPatentService {

    private final FacultyPatentRepository facultyPatentRepository;

    public List<FacultyPatent> getAllPatents() {
        return facultyPatentRepository.findAll();
    }

    public FacultyPatent getPatentById(Long id) {
        return facultyPatentRepository.findById(id).orElse(null);
    }

    public FacultyPatent savePatent(FacultyPatent patent) {
        return facultyPatentRepository.save(patent);
    }

    public FacultyPatent savePatentWithPdf(FacultyPatent patent, MultipartFile pdfFile) throws IOException {
        if (pdfFile != null && !pdfFile.isEmpty()) {
            patent.setCertificatePdf(pdfFile.getBytes());
        }
        return facultyPatentRepository.save(patent);
    }

    public boolean deletePatent(Long id) {
        if (facultyPatentRepository.existsById(id)) {
            facultyPatentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public byte[] getCertificatePdf(Long id) {
        FacultyPatent patent = getPatentById(id);
        return patent != null ? patent.getCertificatePdf() : null;
    }
}
