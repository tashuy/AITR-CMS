package CMSAIML.example.CMSAIML.Controller;

import CMSAIML.example.CMSAIML.Entity.StudentPlacement;
import CMSAIML.example.CMSAIML.Service.StudentPlacementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-placements")
public class StudentPlacementController {

    @Autowired
    private StudentPlacementService studentPlacementService;

    @GetMapping
    public List<StudentPlacement> getAllPlacements() {
        return studentPlacementService.getAllPlacements();
    }

    @GetMapping("/{id}")
    public StudentPlacement getPlacementById(@PathVariable Long id) {
        return studentPlacementService.getPlacementById(id);
    }

    @PostMapping
    public StudentPlacement createPlacement(@RequestBody StudentPlacement placement) {
        return studentPlacementService.createPlacement(placement);
    }

    @PutMapping("/{id}")
    public StudentPlacement updatePlacement(@PathVariable Long id, @RequestBody StudentPlacement placement) {
        placement.setId(id);
        return studentPlacementService.updatePlacement(id,placement);
    }

    @DeleteMapping("/{id}")
    public void deletePlacement(@PathVariable Long id) {
        studentPlacementService.deletePlacement(id);
    }
}
