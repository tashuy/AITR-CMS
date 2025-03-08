package CMSAIML.example.CMSAIML.Controller;

import CMSAIML.example.CMSAIML.Entity.Data;
import CMSAIML.example.CMSAIML.Service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private DataService dataService;

    @GetMapping
    public List<Data> getAllData() {
        return dataService.getAllData();
    }

    @GetMapping("/name/{name}")
    public List<Data> getDataByName(@PathVariable String name) {
        return dataService.getDataByName(name);
    }

    @PostMapping
    public Data createData(@RequestBody Data data) {
        return dataService.createOrUpdateData(data);
    }

    @GetMapping("/{id}")
    public Data getDataById(@PathVariable Long id) {
        return dataService.getDataById(id).orElse(null);  // Return null if not found
    }

    @DeleteMapping("/{id}")
    public void deleteData(@PathVariable Long id) {
        dataService.deleteData(id);
    }
}
