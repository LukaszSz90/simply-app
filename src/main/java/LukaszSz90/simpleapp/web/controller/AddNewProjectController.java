package LukaszSz90.simpleapp.web.controller;

import LukaszSz90.simpleapp.web.command.CreateProjectCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
@Slf4j
@RequiredArgsConstructor
public class AddNewProjectController {

    @GetMapping("/add")
    public String getAddProjectPage(Model model) {
        model.addAttribute(new CreateProjectCommand());
        return "project/add";
    }

}
