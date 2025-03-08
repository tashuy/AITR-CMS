package CMSAIML.example.CMSAIML.repository;
import CMSAIML.example.CMSAIML.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


    List<Student> findByCgpaLessThan(Double cgpa);

    List<Student> findByCgpaGreaterThan(Double cgpa);

    List<Student> findByBranch(String branch);

    List<Student> findByCgpaBetween(Double minCgpa, Double maxCgpa);
}
