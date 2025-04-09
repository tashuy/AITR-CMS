package CMSAIML.example.CMSAIML.Controller;

import CMSAIML.example.CMSAIML.Entity.MouEvent;
import CMSAIML.example.CMSAIML.Service.MouEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/mou-event")
@CrossOrigin
public class MouEventController {

    @Autowired
    private MouEventService service;

    @GetMapping
    public List<MouEvent> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public MouEvent getById(@PathVariable Long id) {
        return service.getById(id).orElse(null);
    }

    @PostMapping
    public MouEvent save(@RequestBody MouEvent event) {
        return service.save(event);
    }

    @PutMapping("/{id}")
    public MouEvent update(@PathVariable Long id, @RequestBody MouEvent event) {
        event.setId(id);
        return service.save(event);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // ✅ Filter Events
    @GetMapping("/filter")
    public List<MouEvent> filterEvents(
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String tag,
            @RequestParam(required = false) String type
    ) {
        return service.filterEvents(department, tag, type);
    }

    // ✅ Upload Certificate or Logo
    @PostMapping("/upload")
    public String uploadLogo(@RequestParam("file") MultipartFile file) {
        return service.uploadFile(file);
    }
}
