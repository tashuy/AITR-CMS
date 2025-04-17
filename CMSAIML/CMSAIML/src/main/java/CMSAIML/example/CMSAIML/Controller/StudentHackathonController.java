package CMSAIML.example.CMSAIML.Controller;

import CMSAIML.example.CMSAIML.Entity.StudentHackathon;
import CMSAIML.example.CMSAIML.Service.StudentHackathonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.math.BigDecimal;

@RestController
@RequestMapping("/hackathons")
// @CrossOrigin(origins = "http://localhost:3000") // Uncomment if React is running locally
public class StudentHackathonController {

    @Autowired
    private StudentHackathonService studentHackathonService;

    @GetMapping
    public List<StudentHackathon> getAllHackathons() {
        return studentHackathonService.getAllHackathons();
    }

    @GetMapping("/{id}")
    public StudentHackathon getHackathonById(@PathVariable Long id) {
        return studentHackathonService.getHackathonById(id);
    }

    @PostMapping("/student-hackathon")
    public ResponseEntity<StudentHackathon> uploadHackathon(
            @RequestParam("studentName") String studentName,
            @RequestParam("enrollmentNumber") String enrollmentNumber,
            @RequestParam("eventName") String eventName,
            @RequestParam("date") String date,
            @RequestParam("teamName") String teamName,
            @RequestParam("teamSize") Integer teamSize,
            @RequestParam("mentorName") String mentorName,
            @RequestParam("hackathonType") String hackathonType,
            @RequestParam("organizingBody") String organizingBody,
            @RequestParam("venue") String venue,
            @RequestParam("problemStatement") String problemStatement,
            @RequestParam("technologyUsed") String technologyUsed,
            @RequestParam("prizeMoney") BigDecimal prizeMoney, // updated to BigDecimal
            @RequestParam("sponsoringCompany") String sponsoringCompany,
            @RequestParam("position") String position,
            @RequestParam("projectGithubLink") String projectGithubLink,
            @RequestParam("projectDescription") String projectDescription,
            @RequestParam("certificateStatus") String certificateStatus,
            @RequestParam("certificatePdf") MultipartFile certificatePdf
    ) throws IOException {

        StudentHackathon hackathon = new StudentHackathon();

        hackathon.setStudentName(studentName);
        hackathon.setEnrollmentNumber(enrollmentNumber);
        hackathon.setEventName(eventName);
        hackathon.setDate(date);
        hackathon.setTeamName(teamName);
        hackathon.setTeamSize(teamSize);
        hackathon.setMentorName(mentorName);
        hackathon.setHackathonType(hackathonType);
        hackathon.setOrganizingBody(organizingBody);
        hackathon.setVenue(venue);
        hackathon.setProblemStatement(problemStatement);
        hackathon.setTechnologyUsed(technologyUsed);
        hackathon.setPrizeMoney(prizeMoney); // no conversion needed
        hackathon.setSponsoringCompany(sponsoringCompany);
        hackathon.setPosition(position);
        hackathon.setProjectGithubLink(projectGithubLink);
        hackathon.setProjectDescription(projectDescription);
        hackathon.setCertificateStatus(certificateStatus);

        if (certificatePdf != null && !certificatePdf.isEmpty()) {
            hackathon.setCertificatePdf(certificatePdf.getBytes());
        }

        return ResponseEntity.ok(studentHackathonService.saveHackathon(hackathon));
    }


    @PutMapping("/{id}")
    public StudentHackathon updateHackathon(@PathVariable Long id, @RequestBody StudentHackathon hackathon) {
        hackathon.setId(id);
        return studentHackathonService.saveHackathon(hackathon);
    }

    @DeleteMapping("/{id}")
    public void deleteHackathon(@PathVariable Long id) {
        studentHackathonService.deleteHackathon(id);
    }
}
