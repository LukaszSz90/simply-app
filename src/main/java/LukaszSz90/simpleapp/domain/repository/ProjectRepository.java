package LukaszSz90.simpleapp.domain.repository;

import LukaszSz90.simpleapp.domain.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {


}
