package LukaszSz90.simpleapp.domain.repository;

import LukaszSz90.simpleapp.domain.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findAllByUserUsername(String username);
}
