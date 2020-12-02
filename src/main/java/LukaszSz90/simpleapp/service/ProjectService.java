package LukaszSz90.simpleapp.service;

import LukaszSz90.simpleapp.converter.ProjectConverter;
import LukaszSz90.simpleapp.domain.model.Project;
import LukaszSz90.simpleapp.domain.model.User;
import LukaszSz90.simpleapp.domain.repository.ProjectRepository;
import LukaszSz90.simpleapp.domain.repository.UserRepository;
import LukaszSz90.simpleapp.web.command.CreateProjectCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Security;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectConverter projectConverter;
    private final UserRepository userRepository;

    @Transactional
    public void add(CreateProjectCommand createProjectCommand) {
        log.debug("Dane do utworzenia projektu: {}", createProjectCommand);

        Project project = projectConverter.from(createProjectCommand);
        updateProjectWithUser();
        log.debug("Projekt do zapisu: {}", project);

        projectRepository.save(project);
        log.debug("Zapisany projekt: {}", project);
    }

    private void updateProjectWithUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.getAuthenticatedUser(username);
    }
}
