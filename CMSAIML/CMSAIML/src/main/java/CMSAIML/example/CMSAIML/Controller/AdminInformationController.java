package CMSAIML.example.CMSAIML.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin-info")
public class AdminInformationController {

    @Autowired
    private CMSAIML.example.CMSAIML.Controller.AdminInformationService service;

    @GetMapping
    public <AdminInformation> List<AdminInformation> getAllAdmins() {
        return service.getAllAdmins();
    }

    @PostMapping
    public <AdminInformation> AdminInformation saveAdmin(@RequestBody AdminInformation admin) {
        return service.saveAdmin(admin);
    }

    @PostMapping("/bulk")
    public <AdminInformation> String saveAdmins(@RequestBody List<AdminInformation> admins) {
        service.saveAllAdmins(admins);
        return "Admins saved successfully!";
    }

    @DeleteMapping
    public String clearAllAdmins() {
        service.clearAll();
        return "All admin records cleared!";
    }
}
