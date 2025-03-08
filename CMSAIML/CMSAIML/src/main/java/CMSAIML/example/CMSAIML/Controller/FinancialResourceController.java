package CMSAIML.example.CMSAIML.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;
import CMSAIML.example.CMSAIML.Entity.FinancialResource;
import CMSAIML.example.CMSAIML.Service.FinancialResourceService;


@RestController
@RequestMapping("/financial-resources")
@CrossOrigin(origins = "*")
public class FinancialResourceController {

    private final FinancialResourceService service;

    public FinancialResourceController(FinancialResourceService service) {
        this.service = service;
    }

    @GetMapping("/{category}/{year}")
    public ResponseEntity<List<FinancialResource>> getFinancialData(@PathVariable String category, @PathVariable Integer year) {
        List<FinancialResource> data = service.getFinancialData(category, year);
        return data.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(data);
    }

    @PostMapping
    public ResponseEntity<FinancialResource> addFinancialData(@RequestBody FinancialResource data) {
        try {
            FinancialResource savedData = service.addFinancialData(data);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
