package LukaszSz90.simpleapp.web.controller;

import LukaszSz90.simpleapp.data.project.ProjectSummary;
import LukaszSz90.simpleapp.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/project")
@Slf4j @RequiredArgsConstructor
public class ProjectListController {

    private final ProjectService projectService;

    @GetMapping("/list")
    public String getProjectListPage(Model model) {
        model.addAttribute("userProjects", projectService.findUserProjects());
        return "project/list";
    }
}
