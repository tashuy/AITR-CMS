package CMSAIML.example.CMSAIML.Entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Date date;
    private String organizer;
    private String description;

    // Default constructor
    public Event() {}

    // Parameterized constructor
    public Event(Long id, String name, Date date, String organizer, String description) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.organizer = organizer;
        this.description = description;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getOrganizer() {
        return organizer;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
