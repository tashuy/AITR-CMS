package CMSAIML.example.CMSAIML.Service;

import CMSAIML.example.CMSAIML.Entity.FacultyFdp;
import CMSAIML.example.CMSAIML.repository.FacultyFdpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacultyFdpService {

    private final FacultyFdpRepository facultyFdpRepository;

    public List<FacultyFdp> getAllFdps() {
        return facultyFdpRepository.findAll();
    }

    public FacultyFdp getFdpById(Long id) {
        return facultyFdpRepository.findById(id).orElse(null);
    }

    public FacultyFdp saveFdp(FacultyFdp fdp) {
        return facultyFdpRepository.save(fdp);
    }

    public FacultyFdp updateFdp(Long id, FacultyFdp updatedFdp) {
        return facultyFdpRepository.findById(id)
                .map(existingFdp -> {
                    updatedFdp.setId(id);
                    return facultyFdpRepository.save(updatedFdp);
                })
                .orElse(null);
    }

    public void deleteFdp(Long id) {
        facultyFdpRepository.deleteById(id);
    }
}
