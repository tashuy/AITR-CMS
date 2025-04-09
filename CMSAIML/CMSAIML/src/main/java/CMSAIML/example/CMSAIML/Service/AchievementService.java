package CMSAIML.example.CMSAIML.Service;

import CMSAIML.example.CMSAIML.Entity.Achievement;
import CMSAIML.example.CMSAIML.repository.AchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AchievementService {

    @Autowired
    private AchievementRepository repository;

    public List<Achievement> getAll() {
        return repository.findAll();
    }

    public Achievement save(Achievement achievement) {
        return repository.save(achievement);
    }

    public Optional<Achievement> getById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    public List<Achievement> filterAchievements(String dept, String tag, String type) {
        return repository.findAll().stream()
                .filter(a -> dept == null || a.getInstituteDepartment().equalsIgnoreCase(dept))
                .filter(a -> tag == null || a.getTagDetails().toLowerCase().contains(tag.toLowerCase()))
                .filter(a -> type == null || a.getAchievementType().equalsIgnoreCase(type))
                .toList();
    }


}
