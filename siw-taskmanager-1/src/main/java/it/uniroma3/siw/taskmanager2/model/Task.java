package it.uniroma3.siw.taskmanager2.model;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;


	@Column(nullable=false)
	private String name;

	@Column(nullable=false)
	private String description;

	@Column(nullable=false)
	private boolean completed;

	@Column(updatable=false,nullable=false)
	private LocalDateTime creationTimeStamp;

	@Column(nullable=false)
	private LocalDateTime lastUpdateTimeStamp;

	//costuttore e getter setter
	public Task() {}
	public Task(String name,String description) {
		this.name=name;
		this.description=description;
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

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public LocalDateTime getCreationTimeStamp() {
		return creationTimeStamp;
	}

	public void setCreationTimeStamp(LocalDateTime creationTimeStamp) {
		this.creationTimeStamp = creationTimeStamp;
	}

	public LocalDateTime getLastUpdateTimeStamp() {
		return lastUpdateTimeStamp;
	}

	public void setLastUpdateTimeStamp(LocalDateTime lastUpdateTimeStamp) {
		this.lastUpdateTimeStamp = lastUpdateTimeStamp;
	}


	//metodo persist dei tempi
	@PrePersist
	protected void onPersist() {
		this.creationTimeStamp=LocalDateTime.now();
		this.lastUpdateTimeStamp=LocalDateTime.now();	
	}

	//metodo persist dei tempi
	@PreUpdate
	protected void onUpdate() {
		this.lastUpdateTimeStamp=LocalDateTime.now();	
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (completed ? 1231 : 1237);
		result = prime * result + ((creationTimeStamp == null) ? 0 : creationTimeStamp.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastUpdateTimeStamp == null) ? 0 : lastUpdateTimeStamp.hashCode());
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
		Task other = (Task) obj;
		if (completed != other.completed)
			return false;
		if (creationTimeStamp == null) {
			if (other.creationTimeStamp != null)
				return false;
		} else if (!creationTimeStamp.equals(other.creationTimeStamp))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastUpdateTimeStamp == null) {
			if (other.lastUpdateTimeStamp != null)
				return false;
		} else if (!lastUpdateTimeStamp.equals(other.lastUpdateTimeStamp))
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
		return "Task [id=" + id + ", name=" + name + ", description=" + description + ", completed=" + completed
				+ ", creationTimeStamp=" + creationTimeStamp + ", lastUpdateTimeStamp=" + lastUpdateTimeStamp + "]";
	}





}
