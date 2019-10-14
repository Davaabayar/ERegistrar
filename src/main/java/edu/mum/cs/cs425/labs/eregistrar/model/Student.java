package edu.mum.cs.cs425.labs.eregistrar.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="students")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long studentId;
	@NotBlank(message="* Student number is required.")
	private String studentNumber;
	
	@NotBlank(message="* First name is required.")
	private String firstName;
	private String middleName;
	
	@NotBlank(message="* Last name is required.")
	private String lastName;
	private String cgpa;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate enrollmentDate;
	
	
	 //@NotBlank(message="* Is international required.") 
	 private Boolean isInternational;
	

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCgpa() {
		return cgpa;
	}

	public void setCgpa(String cgpa) {
		this.cgpa = cgpa;
	}

	public LocalDate getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(LocalDate enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}



	public Student(String studentNumber,
			String firstName, String middleName,
			String lastName, String cgpa, LocalDate enrollmentDate) {
		super();
		this.studentNumber = studentNumber;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.cgpa = cgpa;
		this.enrollmentDate = enrollmentDate;
	}
}
