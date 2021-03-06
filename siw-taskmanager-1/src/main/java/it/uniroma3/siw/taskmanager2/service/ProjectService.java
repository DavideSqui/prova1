package it.uniroma3.siw.taskmanager2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.taskmanager2.model.Project;
import it.uniroma3.siw.taskmanager2.model.User;
import it.uniroma3.siw.taskmanager2.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;


	@Transactional
	public Project getProject(Long id) {
		Optional<Project>result=this.projectRepository.findById(id);
		return result.orElse(null);
	} 


	@Transactional
	public void deleteProject(Project project) {
		this.projectRepository.delete(project);	
	}

	@Transactional
	public Project saveProject(Project project) {
		return this.projectRepository.save(project);	
	}
	
	@Transactional
	public Project shareProjectWithUser(Project project,User user) {
		project.addMember(user);
		return this.projectRepository.save(project);	
	}

}
