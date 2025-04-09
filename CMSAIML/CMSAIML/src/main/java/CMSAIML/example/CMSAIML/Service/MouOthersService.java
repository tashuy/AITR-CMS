package CMSAIML.example.CMSAIML.Service;

import CMSAIML.example.CMSAIML.Entity.MouOthers;
import CMSAIML.example.CMSAIML.repository.MouOthersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MouOthersService {

    @Autowired
    private MouOthersRepository repository;

    public List<MouOthers> getAll() {
        return repository.findAll();
    }

    public MouOthers save(MouOthers data) {
        return repository.save(data);
    }

    public Optional<MouOthers> getById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<MouOthers> filterOthers(String department, String tag) {
        return repository.findAll().stream()
                .filter(o -> department == null || o.getInstituteDepartment().equalsIgnoreCase(department))
                .filter(o -> tag == null || o.getTagDetails().toLowerCase().contains(tag.toLowerCase()))
                .toList();
    }

    public String uploadFile(MultipartFile file) {
        String uploadDir = "uploads/mou-others/";
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        File dest = new File(uploadDir + fileName);

        try {
            dest.getParentFile().mkdirs(); // ensure the directories exist
            file.transferTo(dest);
            return "File uploaded successfully: " + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "File upload failed!";
        }
    }
}
