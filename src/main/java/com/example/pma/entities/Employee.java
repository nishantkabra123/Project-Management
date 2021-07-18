package com.example.pma.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.pma.validators.UniqueValue;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Employee {
//	@GeneratedValue(strategy=GenerationType.IDENTITY) sequence improves performance due to batch updates in hibernate
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="employee_seq")
	@SequenceGenerator(name = "employee_seq", sequenceName = "employee_seq", allocationSize = 1)
	private long employeeId;
	
	@NotBlank
	@Size(min=2,max=50)
	private String firstName;
	
	@NotBlank(message="must give a last name")
	@Size(min=2,max=50)
	private String lastName;
	//controller level validator will catch it at client side,nullable will error out at db level
	//auto ddl auto is set to none so column unique is not applied as db constraint
	@NotNull
	@Email(message="must be a valid email address")
	@UniqueValue
	private String email;
//	@Column(unique = true/* ,nullable=false */)
//	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST},
//			fetch=FetchType.LAZY)
//	@JoinColumn(name="project_id")
	@ManyToMany(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST},
			fetch=FetchType.LAZY)
	@JoinTable(name="project_employee",
			joinColumns=@JoinColumn(name="employee_id"),
			inverseJoinColumns=@JoinColumn(name="project_id"))
	@JsonIgnore
	private List<Project> projects;
//	private Project theProject;
//	
//	public Project getTheProject() {
//		return theProject;
//	}
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
//	public void setTheProject(Project theProject) {
//		this.theProject = theProject;
//	}
	public Employee() {
		
	}
	public Employee(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", projects=" + projects + "]";
	}

}
