package CMSAIML.example.CMSAIML.Controller;

import CMSAIML.example.CMSAIML.Entity.MouOthers;
import CMSAIML.example.CMSAIML.Service.MouOthersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/mou-others")
@CrossOrigin
public class MouOthersController {

    @Autowired
    private MouOthersService service;

    @GetMapping
    public List<MouOthers> getAll() {
        return service.getAll();
    }

    @PostMapping
    public MouOthers save(@RequestBody MouOthers data) {
        return service.save(data);
    }

    @PutMapping("/{id}")
    public MouOthers update(@PathVariable Long id, @RequestBody MouOthers data) {
        data.setId(id);
        return service.save(data);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/filter")
    public List<MouOthers> filter(
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String tag
    ) {
        return service.filterOthers(department, tag);
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        return service.uploadFile(file);
    }

    @GetMapping("/{id}")
    public MouOthers getById(@PathVariable Long id) {
        return service.getById(id).orElse(null);
    }
}
