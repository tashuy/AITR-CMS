package CMSAIML.example.CMSAIML.Controller;

import CMSAIML.example.CMSAIML.Entity.Achievement;
import CMSAIML.example.CMSAIML.Service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/achievement")
@CrossOrigin
public class AchievementController {

    @Autowired
    private AchievementService service;

    @GetMapping
    public List<Achievement> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Achievement save(@RequestBody Achievement achievement) {
        return service.save(achievement);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Achievement> getById(@PathVariable Long id) {
        Optional<Achievement> achievement = service.getById(id);
        return achievement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Achievement> update(@PathVariable Long id, @RequestBody Achievement updatedAchievement) {
        if (!service.getById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        updatedAchievement.setId(id);
        return ResponseEntity.ok(service.save(updatedAchievement));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!service.getById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    // ------------------- File Upload -------------------

    @PostMapping("/{id}/upload-certificate")
    public ResponseEntity<String> uploadCertificate(@PathVariable Long id,
                                                    @RequestParam("file") MultipartFile file) throws IOException {
        Optional<Achievement> optional = service.getById(id);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Achievement achievement = optional.get();

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        // Here, store the file where you want and save the path or base64
        // For now, let's assume we save the filename
        achievement.setCertificateFile(fileName);

        service.save(achievement);
        return ResponseEntity.ok("File uploaded successfully.");
    }

    // ------------------- Filtering -------------------

    @GetMapping("/filter")
    public List<Achievement> filterAchievements(
            @RequestParam(required = false) String instituteDepartment,
            @RequestParam(required = false) String tagDetails,
            @RequestParam(required = false) String achievementType) {
        return service.filterAchievements(instituteDepartment, tagDetails, achievementType);
    }
}
