package CMSAIML.example.CMSAIML.Controller;

import CMSAIML.example.CMSAIML.Entity.FacultyFdp;
import CMSAIML.example.CMSAIML.Service.FacultyFdpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculty-fdps")
public class FacultyFdpController {

    @Autowired
    private FacultyFdpService facultyFdpService;

    @GetMapping
    public List<FacultyFdp> getAllFdps() {
        return facultyFdpService.getAllFdps();
    }

    @GetMapping("/{id}")
    public FacultyFdp getFdpById(@PathVariable Long id) {
        return facultyFdpService.getFdpById(id);
    }

    @PostMapping
    public FacultyFdp createFdp(@RequestBody FacultyFdp fdp) {
        return facultyFdpService.saveFdp(fdp);
    }

    @PutMapping("/{id}")
    public FacultyFdp updateFdp(@PathVariable Long id, @RequestBody FacultyFdp fdp) {
        fdp.setId(id);
        return facultyFdpService.saveFdp(fdp);
    }

    @DeleteMapping("/{id}")
    public void deleteFdp(@PathVariable Long id) {
        facultyFdpService.deleteFdp(id);
    }
}

