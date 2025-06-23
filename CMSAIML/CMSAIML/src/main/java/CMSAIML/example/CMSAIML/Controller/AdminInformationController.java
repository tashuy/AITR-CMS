package CMSAIML.example.CMSAIML.Controller;

import CMSAIML.example.CMSAIML.Entity.AdminInformation;
import org.springframework.beans.factory.annotation.Autowired;
import CMSAIML.example.CMSAIML.Service.AdminInformationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin-info")
public class AdminInformationController {

    @Autowired
    private AdminInformationService service;

    @GetMapping("adminInformation")
    public List<AdminInformation> getAllAdmins() {
        return service.getAllAdmins();
    }

    @PostMapping
    public AdminInformation saveAdmin(@RequestBody AdminInformation admin) {
        return service.saveAdmin(admin);
    }

    @PostMapping("/bulk")
    public String saveAdmins(@RequestBody List<AdminInformation> admins) {
        service.saveAllAdmins(admins);
        return "Admins saved successfully!";
    }

    @DeleteMapping
    public String clearAllAdmins() {
        service.clearAll();
        return "All admin records cleared!";
    }
}