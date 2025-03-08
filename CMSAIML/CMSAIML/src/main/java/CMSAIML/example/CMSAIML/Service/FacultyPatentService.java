package CMSAIML.example.CMSAIML.Service;

import CMSAIML.example.CMSAIML.Entity.FacultyPatent;
import CMSAIML.example.CMSAIML.repository.FacultyPatentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyPatentService {

    @Autowired
    private FacultyPatentRepository facultyPatentRepository;

    public List<FacultyPatent> getAllPatents() {
        return facultyPatentRepository.findAll();
    }

    public FacultyPatent getPatentById(Long id) {
        return facultyPatentRepository.findById(id).orElse(null);
    }

    public FacultyPatent savePatent(FacultyPatent patent) {
        return facultyPatentRepository.save(patent);
    }

    public void deletePatent(Long id) {
        facultyPatentRepository.deleteById(id);
    }
}
