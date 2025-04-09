package CMSAIML.example.CMSAIML.Service;

import CMSAIML.example.CMSAIML.Entity.MouCollab;
import CMSAIML.example.CMSAIML.repository.MouCollabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MouCollabService {

    @Autowired
    private MouCollabRepository repository;

    public List<MouCollab> getAll() {
        return repository.findAll();
    }

    public MouCollab save(MouCollab collab) {
        return repository.save(collab);
    }

    public Optional<MouCollab> getById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    // ✅ Filtering
    public List<MouCollab> filterCollabs(String department, String tag, String type) {
        return repository.findAll().stream()
                .filter(c -> department == null || c.getInstituteDepartment().equalsIgnoreCase(department))
                .filter(c -> tag == null || c.getTagDetails().toLowerCase().contains(tag.toLowerCase()))
                .filter(c -> type == null || c.getCollaborationType().equalsIgnoreCase(type))
                .toList();
    }

    // ✅ Logo upload
    public String uploadLogo(MultipartFile file) {
        if (file.isEmpty()) {
            return "File is empty.";
        }

        try {
            String uploadDir = "uploads/logos/";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String uniqueFilename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            String filePath = uploadDir + uniqueFilename;

            file.transferTo(new File(filePath));
            return filePath; // This path can be stored in `organizationLogo` field
        } catch (IOException e) {
            e.printStackTrace();
            return "Error uploading file: " + e.getMessage();
        }
    }
}
