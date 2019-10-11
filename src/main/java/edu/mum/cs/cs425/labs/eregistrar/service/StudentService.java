package edu.mum.cs.cs425.labs.eregistrar.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

import edu.mum.cs.cs425.labs.eregistrar.model.Student;

public interface StudentService {
	public abstract Iterable<Student> getAllStudents();
	public abstract Page<Student> getAllStudentsPaged(int pageno);
	public abstract Student saveStudent(Student student);
}
