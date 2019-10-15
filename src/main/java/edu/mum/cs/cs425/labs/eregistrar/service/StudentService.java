package edu.mum.cs.cs425.labs.eregistrar.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.mum.cs.cs425.labs.eregistrar.model.Student;

public interface StudentService {
	public abstract Iterable<Student> getAllStudents();
	public abstract Page<Student> getAllStudentsPaged(int pageno);
	public abstract Student saveStudent(Student student);
	public abstract Student getStudentByStudentId(Long studentId);
	public abstract Student updateStudentById(Student editedStudent, Long studentId);
	public abstract void deleteStudentById(Long studentId);
	public abstract List<Student> searchStudents(String query);
	public abstract List<Student> findByFirstName(String firstName);
	public abstract Page<Student> searchStudentsPaged(String query, String type,int pageno);
	
}
