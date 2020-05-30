package it.uniroma3.siw.taskmanager2;




import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.uniroma3.siw.taskmanager2.model.Project;
import it.uniroma3.siw.taskmanager2.model.User;
import it.uniroma3.siw.taskmanager2.repository.ProjectRepository;
import it.uniroma3.siw.taskmanager2.repository.TaskRepository;
import it.uniroma3.siw.taskmanager2.repository.UserRepository;
import it.uniroma3.siw.taskmanager2.service.ProjectService;
import it.uniroma3.siw.taskmanager2.service.TaskService;
import it.uniroma3.siw.taskmanager2.service.UserService;

@SpringBootTest
@RunWith(SpringRunner.class)
class SiwTaskmanagerApplicationTests {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private TaskService taskService;


	@Before
	public void deleteAll(){
		System.out.println("Eliminando i precedenti file . . .");
		this.userRepository.deleteAll();
		this.projectRepository.deleteAll();
		this.taskRepository.deleteAll();
	}

	@Test
	void testUpdateUser() {
		User user1=new User("mariorossi","password","Mario","Rossi");
		user1=userService.saveUser(user1);
		assertEquals(user1.getFirstName(), "Mario");
		assertEquals(user1.getId().longValue(),1L);

		User user2=new User("lucabianchi","password","Luca","Bianchi");
		user2=userService.saveUser(user2);
		assertEquals(user2.getFirstName(), "Luca");
		assertEquals(user2.getId().longValue(),2L);

		//Ora faccio update su user1.
		User user1update=new User("mariarossi","password","Maria","Rossi");
		user1update.setId(user1.getId());
		user1=userService.saveUser(user1);
		//da mario rossi a maria rossi.
		assertEquals(user1update.getFirstName(), "Maria");
		assertEquals(user1update.getId().longValue(),1L);

		
		/*Costruiamo i due progetti,creqati da user1 nuovo.*/
		Project project1=new Project("Progetto1","Un semplice progetto");
		project1=projectService.saveProject(project1);
		project1.setOwner(user1update);
		assertEquals(project1.getOwner(),user1update);
		assertEquals(project1.getName(),"Progetto1");

		
		//vediamo se risolvo la nullexeption
		
		Project project2=new Project("Progetto2","Un altro semplice progetto");
		project2.setOwner(user1update);
		project2=projectService.saveProject(project2);
		assertEquals(project2.getOwner(),user1update);
		assertEquals(project2.getName(),"Progetto2");

		//condivido progetto1 con user2 
		project1=projectService.shareProjectWithUser(project1,user2);

		List<Project> projects=projectRepository.findByOwner(user1update);
		assertEquals(projects.size(),2);
		assertEquals(projects.get(0),project1);
		assertEquals(projects.get(1),project2);

		List <Project> projectVisibleByUser2=projectRepository.findByMembers(user2);
		assertEquals(projectVisibleByUser2.size(),1);
		assertEquals(projectVisibleByUser2.get(0),project1);

		List<User> project1Members=userRepository.findByVisibleProjects(project1);
		assertEquals(project1Members.size(),1);
		assertEquals(project1Members.get(0),user2);


	}

}
