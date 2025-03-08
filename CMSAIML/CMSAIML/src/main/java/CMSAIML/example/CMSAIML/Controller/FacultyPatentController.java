package CMSAIML.example.CMSAIML.Controller;

import CMSAIML.example.CMSAIML.Entity.FacultyPatent;
import CMSAIML.example.CMSAIML.Service.FacultyPatentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculty-patents")
public class FacultyPatentController {

    @Autowired
    private FacultyPatentService facultyPatentService;

    @GetMapping
    public List<FacultyPatent> getAllPatents() {
        return facultyPatentService.getAllPatents();
    }

    @GetMapping("/{id}")
    public FacultyPatent getPatentById(@PathVariable Long id) {
        return facultyPatentService.getPatentById(id);
    }

    @PostMapping
    public FacultyPatent createPatent(@RequestBody FacultyPatent patent) {
        return facultyPatentService.savePatent(patent);
    }

    @PutMapping("/{id}")
    public FacultyPatent updatePatent(@PathVariable Long id, @RequestBody FacultyPatent patent) {
        patent.setId(id);
        return facultyPatentService.savePatent(patent);
    }

    @DeleteMapping("/{id}")
    public void deletePatent(@PathVariable Long id) {
        facultyPatentService.deletePatent(id);
    }
}
