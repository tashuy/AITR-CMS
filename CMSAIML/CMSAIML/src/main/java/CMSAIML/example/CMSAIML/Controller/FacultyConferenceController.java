package CMSAIML.example.CMSAIML.Controller;

import CMSAIML.example.CMSAIML.Entity.FacultyConference;
import CMSAIML.example.CMSAIML.Service.FacultyConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculty-conferences")
public class FacultyConferenceController {

    @Autowired
    private FacultyConferenceService facultyConferenceService;

    @GetMapping
    public List<FacultyConference> getAllConferences() {
        return facultyConferenceService.getAllConferences();
    }

    @GetMapping("/{id}")
    public FacultyConference getConferenceById(@PathVariable Long id) {
        return facultyConferenceService.getConferenceById(id);
    }

    @PostMapping
    public FacultyConference createConference(@RequestBody FacultyConference conference) {
        return facultyConferenceService.saveConference(conference);
    }

    @PutMapping("/{id}")
    public FacultyConference updateConference(@PathVariable Long id, @RequestBody FacultyConference conference) {
        conference.setId(id);
        return facultyConferenceService.saveConference(conference);
    }

    @DeleteMapping("/{id}")
    public void deleteConference(@PathVariable Long id) {
        facultyConferenceService.deleteConference(id);
    }
}
