package CMSAIML.example.CMSAIML.repository;

import CMSAIML.example.CMSAIML.Entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    // Find by department name instead of department ID
    @Query("SELECT f FROM Faculty f WHERE f.department = ?1")
    List<Faculty> findByDepartment(String department);

    // Custom query to find faculties by email
    @Query("SELECT f FROM Faculty f WHERE f.email = ?1")
    List<Faculty> findByEmail(String email);
}
