package edu.mum.cs.cs425.labs.eregistrar.serviceimp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

}
