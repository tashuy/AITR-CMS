package CMSAIML.example.CMSAIML.Service;

import CMSAIML.example.CMSAIML.Entity.StudentPlacement;
import CMSAIML.example.CMSAIML.repository.StudentPlacementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentPlacementService  {

    @Autowired
    private StudentPlacementRepository studentPlacementRepository;

    public List<StudentPlacement> getAllPlacements() {
        return studentPlacementRepository.findAll();
    }

    public StudentPlacement getPlacementById(Long id) {
        return studentPlacementRepository.findById(id).orElse(null);
    }

    public StudentPlacement createPlacement(StudentPlacement placement) {
        return studentPlacementRepository.save(placement);
    }

    public StudentPlacement updatePlacement(Long id, StudentPlacement placementDetails) {
        StudentPlacement placement = studentPlacementRepository.findById(id).orElse(null);
        if (placement != null) {
            placement.setStudent_name(placementDetails.getStudent_name());
            placement.setCompany_name(placementDetails.getCompany_name());
            placement.setPackage(placementDetails.getPackage());
            placement.setJoining_date(placementDetails.getJoining_date());
            placement.setOffer_letter_link(placementDetails.getOffer_letter_link());
            return studentPlacementRepository.save(placement);
        }
        return null;
    }

    public void deletePlacement(Long id) {
        studentPlacementRepository.deleteById(id);
    }
}
