package LukaszSz90.simpleapp.service;

import LukaszSz90.simpleapp.web.command.CreateProjectCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectService {
    public void add(CreateProjectCommand createProjectCommand) {
    }
}
