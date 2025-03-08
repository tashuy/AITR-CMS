package CMSAIML.example.CMSAIML.Controller;

import CMSAIML.example.CMSAIML.Entity.StudentHackathon;
import CMSAIML.example.CMSAIML.Service.StudentHackathonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hackathons")
public class StudentHackathonController {

    @Autowired
    private StudentHackathonService studentHackathonService;

    @GetMapping
    public List<StudentHackathon> getAllHackathons() {
        return studentHackathonService.getAllHackathons();
    }

    @GetMapping("/{id}")
    public StudentHackathon getHackathonById(@PathVariable Long id) {
        return studentHackathonService.getHackathonById(id);
    }

    @PostMapping
    public StudentHackathon addHackathon(@RequestBody StudentHackathon hackathon) {
        return studentHackathonService.saveHackathon(hackathon);
    }

    @PutMapping("/{id}")
    public StudentHackathon updateHackathon(@PathVariable Long id, @RequestBody StudentHackathon hackathon) {
        hackathon.setId(id);
        return studentHackathonService.saveHackathon(hackathon);
    }

    @DeleteMapping("/{id}")
    public void deleteHackathon(@PathVariable Long id) {
        studentHackathonService.deleteHackathon(id);
    }
}
