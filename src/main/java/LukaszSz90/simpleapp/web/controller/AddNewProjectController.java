package LukaszSz90.simpleapp.web.controller;

import LukaszSz90.simpleapp.service.ProjectService;
import LukaszSz90.simpleapp.web.command.CreateProjectCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/project")
@Slf4j
@RequiredArgsConstructor
public class AddNewProjectController {

    private final ProjectService projectService;

    @GetMapping("/add")
    public String getAddProjectPage(Model model) {
        model.addAttribute(new CreateProjectCommand());
        return "project/add";
    }

    @PostMapping
    public String processAddProject(@Valid CreateProjectCommand createProjectCommand, BindingResult bindings) {
        log.debug("Dane do utworzenia projektu: {}", createProjectCommand);
        if (bindings.hasErrors()) {
            log.debug("Dane zawierają błędy: {}", bindings.getAllErrors());
            return "project/add";
        }

        try {
            projectService.add(createProjectCommand);
            log.debug("Utworzono projekt");
            return "redirect:/project";
        }
        catch (RuntimeException re) {
            log.warn(re.getLocalizedMessage());
            log.debug("Bład podczas tworzenia projetu", re);
            bindings.rejectValue(null, null, "Wystąpił bład");
            return "project/add";
        }
    }

}
