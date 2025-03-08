package CMSAIML.example.CMSAIML.Service;

import CMSAIML.example.CMSAIML.Entity.Data;
import CMSAIML.example.CMSAIML.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataService {

    @Autowired
    private DataRepository dataRepository;

    // Fetch all data from the database
    public List<Data> getAllData() {
        return dataRepository.findAll();
    }

    // Create or update data
    public Data createOrUpdateData(Data data) {
        return dataRepository.save(data);  // Saves or updates the data
    }

    // Fetch data by name
    public List<Data> getDataByName(String name) {
        return dataRepository.findByName(name);
    }

    // Fetch data by id
    public Optional<Data> getDataById(Long id) {
        return dataRepository.findById(id);  // Returns Optional<Data>
    }

    // Delete data by id
    public void deleteData(Long id) {
        dataRepository.deleteById(id);
    }
}
