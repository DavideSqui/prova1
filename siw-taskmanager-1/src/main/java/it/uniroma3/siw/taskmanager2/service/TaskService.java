package it.uniroma3.siw.taskmanager2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.taskmanager2.model.Task;

import it.uniroma3.siw.taskmanager2.repository.TaskRepository;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;

	@Transactional
	public Task getTask(Long id) {
		Optional<Task> result=this.taskRepository.findById(id);
		return result.orElse(null);
	} 

	@Transactional
	public void deleteTask(Task task) {
		this.taskRepository.delete(task);	
	}

	@Transactional
	public Task saveTask(Task task) {
		return this.taskRepository.save(task);	
	}
	
	public Task setCompleted(Task task) {
		task.setCompleted(true);
		return this.taskRepository.save(task);	
	}




}
