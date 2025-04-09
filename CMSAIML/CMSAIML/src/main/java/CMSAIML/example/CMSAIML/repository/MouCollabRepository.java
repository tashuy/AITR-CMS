package CMSAIML.example.CMSAIML.repository;


import CMSAIML.example.CMSAIML.Entity.MouCollab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MouCollabRepository extends JpaRepository<MouCollab, Long> {
}
