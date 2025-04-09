package CMSAIML.example.CMSAIML.Service;

import CMSAIML.example.CMSAIML.Entity.MouEvent;
import CMSAIML.example.CMSAIML.repository.MouEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MouEventService {

    @Autowired
    private MouEventRepository repository;

    public List<MouEvent> getAll() {
        return repository.findAll();
    }

    public Optional<MouEvent> getById(Long id) {
        return repository.findById(id);
    }

    public MouEvent save(MouEvent event) {
        return repository.save(event);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    // ✅ Filter by department, tag, type
    public List<MouEvent> filterEvents(String dept, String tag, String type) {
        return repository.findAll().stream()
                .filter(e -> dept == null || e.getInstituteDepartment().equalsIgnoreCase(dept))
                .filter(e -> tag == null || e.getTagDetails().toLowerCase().contains(tag.toLowerCase()))
                .filter(e -> type == null || e.getEventType().equalsIgnoreCase(type))
                .toList();
    }

    // ✅ Upload logo or certificate
    public String uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            return "File is empty!";
        }

        try {
            String uploadDir = "uploads/mou-events/";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            String fullPath = uploadDir + filename;

            file.transferTo(new File(fullPath));

            return "File uploaded successfully: " + fullPath;
        } catch (IOException e) {
            e.printStackTrace();
            return "File upload failed!";
        }
    }
}
