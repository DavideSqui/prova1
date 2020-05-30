package it.uniroma3.siw.taskmanager2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.taskmanager2.model.User;
import it.uniroma3.siw.taskmanager2.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public User getUser(Long id) {
		Optional<User>result=this.userRepository.findById(id);
		return result.orElse(null);
	} 

	@Transactional
	public User getUser(String username) {
		Optional<User>result=this.userRepository.findByUsername(username);
		return result.orElse(null);
	}

	@Transactional
	public User saveUser(User user) {
		return this.userRepository.save(user);	
	}
	
	public List<User> findAllUsers(User user){
		Iterable<User> i=this.userRepository.findAll();
		ArrayList<User> lista=new ArrayList<>();
		//visualizza tutti e li inserisce uno per uno
		for(User u : i) {
			lista.add(u);
		}
		return lista;
	}




}
