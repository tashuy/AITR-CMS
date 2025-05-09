package CMSAIML.example.CMSAIML.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping("/{path:[^\\.]*}")
    public RedirectView redirect() {
        return new RedirectView("/");
    }
}
