package dci.j24e1.group1.demotestmap.controllers;

import dci.j24e1.group1.demotestmap.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AppController {

    @Autowired
    private VacationService vacationService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("vacationPoints", vacationService.getAllVacationPoints());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/submit")
    public String submitVacationPoint(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam(required = false) MultipartFile[] photos,
            @RequestParam(required = false) String route
    ) {
        vacationService.saveVacationPoint(title, description, latitude, longitude, photos, route);
        return "redirect:/";
    }


}
