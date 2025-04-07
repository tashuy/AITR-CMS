package CMSAIML.example.CMSAIML.Service;

import CMSAIML.example.CMSAIML.Entity.StudentCertificate;
import CMSAIML.example.CMSAIML.repository.StudentCertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCertificateService {

    @Autowired
    private StudentCertificateRepository studentCertificateRepository;

    public List<StudentCertificate> getAllCertificates() {
        return studentCertificateRepository.findAll();
    }

    public StudentCertificate getCertificateById(Long id) {
        return studentCertificateRepository.findById(id).orElse(null);
    }

    public StudentCertificate saveCertificate(StudentCertificate certificate) {
        return studentCertificateRepository.save(certificate);
    }

    public StudentCertificate updateCertificate(Long id, StudentCertificate updatedCertificate) {
        return studentCertificateRepository.findById(id)
                .map(existing -> {
                    updatedCertificate.setId(id);
                    return studentCertificateRepository.save(updatedCertificate);
                })
                .orElse(null);
    }

    public void deleteCertificate(Long id) {
        studentCertificateRepository.deleteById(id);
    }
}
