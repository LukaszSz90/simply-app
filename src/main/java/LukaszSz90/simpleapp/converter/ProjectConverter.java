package LukaszSz90.simpleapp.converter;

import LukaszSz90.simpleapp.domain.model.Project;
import LukaszSz90.simpleapp.web.command.CreateProjectCommand;
import org.springframework.stereotype.Component;

@Component
public class ProjectConverter {
    public Project from(CreateProjectCommand createProjectCommand) {
        return Project.builder()
                .name(createProjectCommand.getName())
                .url(createProjectCommand.getUrl())
                .description(createProjectCommand.getDescription())
                .build();
    }
}
