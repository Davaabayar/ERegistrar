package edu.mum.cs.cs425.labs.eregistrar.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import edu.mum.cs.cs425.labs.eregistrar.model.Student;
import edu.mum.cs.cs425.labs.eregistrar.repository.StudentRepository;
import edu.mum.cs.cs425.labs.eregistrar.service.StudentService;

@Service
public class StudentServiceimp implements StudentService{

	@Autowired
	private StudentRepository repository;
	
	@Override
	public Iterable<Student> getAllStudents() {
		return repository.findAll(Sort.by("studentNumber"));
	}

	@Override
	public Page<Student> getAllStudentsPaged(int pageno) {
		return repository.findAll(PageRequest.of(pageno,5, Sort.by("studentNumber")));
	}

	@Override
	public Student saveStudent(Student student) {
		return repository.save(student);
	}
	
	@Override
	public Student getStudentByStudentId(Long studentId) {
		return repository.findById(studentId).orElse(null);
	}

	@Override
	public Student updateStudentById(Student editedStudent, Long studentId) {
		return repository.findById(studentId)
				.map(objToUpdate -> {
					objToUpdate.setCgpa(editedStudent.getCgpa());
					objToUpdate.setEnrollmentDate(editedStudent.getEnrollmentDate());
					objToUpdate.setFirstName(editedStudent.getFirstName());
					objToUpdate.setLastName(editedStudent.getLastName());
					objToUpdate.setMiddleName(editedStudent.getMiddleName());
					objToUpdate.setStudentNumber(editedStudent.getStudentNumber());
					return repository.save(objToUpdate);
				}).orElseGet(()->{
					return repository.save(editedStudent);
				});
	}

	@Override
	public void deleteStudentById(Long studentId) {
		repository.deleteById(studentId);
	}
	 @Override
	 public List<Student> searchStudents(String searchString) {	       
		 return repository.searchStudents(searchString);
	 }
	 @Override
	public List<Student> findByFirstName(String firstName) {		
		return repository.findByFirstName(firstName);
	}

	@Override
	public Page<Student> searchStudentsPaged(String query, String type, int pageno) {
		if(query==null)
			query = "";
		
		if(type!=null) {
			switch (type) {
			case "name":
				return repository.searchStudentsByNamePaged(query, PageRequest.of(pageno, 3, Sort.by(Direction.ASC, "firstName")));
			case "studentNumber":
				return repository.searchStudentsByNumberPaged(query, PageRequest.of(pageno, 3, Sort.by(Direction.ASC,"firstName")));	
			case "gpa":
				return repository.searchStudentsByGpaPaged(query, PageRequest.of(pageno,3,Sort.by(Direction.ASC,"firstName")));
			}
		}
		return repository.searchStudentsByNamePaged(query, PageRequest.of(pageno, 3, Sort.by(Direction.ASC,"firstName")));
	}
	 
}
