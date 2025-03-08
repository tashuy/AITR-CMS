package CMSAIML.example.CMSAIML.Service;

import CMSAIML.example.CMSAIML.Entity.FacultyConference;
import CMSAIML.example.CMSAIML.repository.FacultyConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyConferenceService {

    @Autowired
    private FacultyConferenceRepository facultyConferenceRepository;

    public List<FacultyConference> getAllConferences() {
        return facultyConferenceRepository.findAll();
    }

    public FacultyConference getConferenceById(Long id) {
        return facultyConferenceRepository.findById(id).orElse(null);
    }

    public FacultyConference saveConference(FacultyConference conference) {
        return facultyConferenceRepository.save(conference);
    }

    public void deleteConference(Long id) {
        facultyConferenceRepository.deleteById(id);
    }
}
