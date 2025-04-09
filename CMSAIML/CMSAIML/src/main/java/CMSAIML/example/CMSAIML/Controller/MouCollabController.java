package CMSAIML.example.CMSAIML.Controller;

import CMSAIML.example.CMSAIML.Entity.MouCollab;
import CMSAIML.example.CMSAIML.Service.MouCollabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/mou-collab")
@CrossOrigin
public class MouCollabController {

    @Autowired
    private MouCollabService service;

    @GetMapping
    public List<MouCollab> getAll() {
        return service.getAll();
    }

    @PostMapping
    public MouCollab save(@RequestBody MouCollab collab) {
        return service.save(collab);
    }

    @PutMapping("/{id}")
    public MouCollab update(@PathVariable Long id, @RequestBody MouCollab collab) {
        collab.setId(id);
        return service.save(collab);
    }

    @GetMapping("/{id}")
    public MouCollab getById(@PathVariable Long id) {
        return service.getById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // ✅ Filtering
    @GetMapping("/filter")
    public List<MouCollab> filterCollabs(
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String tag,
            @RequestParam(required = false) String type
    ) {
        return service.filterCollabs(department, tag, type);
    }

    // ✅ Logo File Upload
    @PostMapping("/upload-logo")
    public String uploadLogo(@RequestParam("file") MultipartFile file) {
        return service.uploadLogo(file); // Implement this in your service
    }
}
