package it.uniroma3.siw.taskmanager2.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.taskmanager2.model.Project;
import it.uniroma3.siw.taskmanager2.model.User;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long>{

public List<Project> findByMembers(User member);

public List<Project> findByOwner(User owner);

}
