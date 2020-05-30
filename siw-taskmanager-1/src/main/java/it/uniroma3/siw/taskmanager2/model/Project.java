package it.uniroma3.siw.taskmanager2.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(nullable=false)
	private String name;

	@Column(nullable=false)
	private String description;

	//versione inversa,gi� � eager ma ce lo segno:
	@ManyToOne(fetch=FetchType.EAGER)
	private User owner;

	//questo many to many non ha scritto mapped by
	@ManyToMany
	private List<User> members;

	//one to many unidirezionale con unica join table verso task invece di mappedby
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.REMOVE)
	@JoinColumn(name="project_id")
	private List<Task> taskList;

	//costuttore e getter setter

	public Project() {		}

	public Project(String name, String description) {
		this.name=name;
		this.description=description;
	}
	
	public Project(String name, String description, List <User> members) {
		this.name=name;
		this.description=description;
		this.members=members;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<User> getVisibleUsers() {
		return members;
	}

	public void setVisibleUsers(List<User> members) {
		this.members = members;
	}

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}


	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	//METODO CHE AGGIUNGE UNO USER AL PROJECT 
	public void addMember(User member) {
		// TODO Auto-generated method stub
		this.members.add(member);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", description=" + description + ", owner=" + owner
				+ ", members=" + members + ", taskList=" + taskList + "]";
	}





}
