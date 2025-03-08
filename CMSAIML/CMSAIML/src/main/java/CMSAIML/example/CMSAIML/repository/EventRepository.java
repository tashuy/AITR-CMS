package CMSAIML.example.CMSAIML.repository;

import CMSAIML.example.CMSAIML.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {


}
