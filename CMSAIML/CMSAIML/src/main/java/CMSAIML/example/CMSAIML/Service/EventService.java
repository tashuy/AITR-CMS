package CMSAIML.example.CMSAIML.Service;

import CMSAIML.example.CMSAIML.Entity.Event;
import java.util.List;

public interface EventService {
    List<Event> getAllEvents();
    Event getEventById(Long id);
    Event createEvent(Event event);
    Event updateEvent(Long id, Event event);
    void deleteEvent(Long id);
}
