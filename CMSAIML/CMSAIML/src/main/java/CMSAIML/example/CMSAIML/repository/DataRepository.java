package CMSAIML.example.CMSAIML.repository;

import CMSAIML.example.CMSAIML.Entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface DataRepository extends JpaRepository<Data, Long> {

    // You can define custom query methods here if needed
    // For example:
    List<Data> findByName(String name);  // Find data by name

    // You can also add more complex queries if needed:
    @Query("SELECT d FROM Data d WHERE d.description LIKE %:description%")
    List<Data> findByDescriptionContaining(@Param("description") String description);
}
