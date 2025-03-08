package CMSAIML.example.CMSAIML.Service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;
import CMSAIML.example.CMSAIML.Entity.FinancialResource;
import CMSAIML.example.CMSAIML.repository.FinancialResourceRepository;
@Service
public class FinancialResourceService {

    private final FinancialResourceRepository repository;

    public FinancialResourceService(FinancialResourceRepository repository) {
        this.repository = repository;
    }

    public List<FinancialResource> getFinancialData(String category, Integer year) {
        return repository.findByCategoryAndYear(category, year);
    }

    public FinancialResource addFinancialData(FinancialResource data) {
        return repository.save(data);
    }
}
