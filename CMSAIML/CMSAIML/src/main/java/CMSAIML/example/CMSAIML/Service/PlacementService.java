package CMSAIML.example.CMSAIML.Service;

import CMSAIML.example.CMSAIML.Entity.Placement;
import java.util.List;
import java.util.Optional;

public interface PlacementService {

    List<Placement> getAllPlacements();  // Fetch all placements

    Optional<Placement> getPlacementById(Long id);  // Fetch placement by ID

    Placement createPlacement(Placement placement);  // Create a new placement

    Placement updatePlacement(Long id, Placement placement);  // Update an existing placement

    void deletePlacement(Long id);  // Delete placement by ID
}
