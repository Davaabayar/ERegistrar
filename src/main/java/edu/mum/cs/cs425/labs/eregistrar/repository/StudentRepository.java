package edu.mum.cs.cs425.labs.eregistrar.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import edu.mum.cs.cs425.labs.eregistrar.model.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	/*List<Student> findAllByFistNameContainingOrLastNameContaining(
			String searchString, String searchString2);*/
	
	@Query("select s from Student s where s.firstName like %?1% order by s.firstName")
    List<Student> searchStudents(String query);
	
	@Query("select s from Student s where s.firstName like %?1% or s.lastName like %?1% or s.middleName like %?1% order by s.firstName")
    Page<Student> searchStudentsByNamePaged(String query, Pageable pageable);
	
	@Query("select s from Student s where s.studentNumber like %?1% order by s.firstName")
    Page<Student> searchStudentsByNumberPaged(String query, Pageable pageable);
	
	@Query("select s from Student s where s.cgpa>=?1 order by s.firstName")
    Page<Student> searchStudentsByGpaPaged(String query, Pageable pageable);
	
	@Query("select s from Student s where s.enrollmentDate>=STR_TO_DATE(?1,'%y-%m-%d') order by s.firstName")
    Page<Student> searchStudentsByEnrolledPaged(String query, Pageable pageable);
	
	List<Student> findByFirstName(String firstName);	
	
	
}
