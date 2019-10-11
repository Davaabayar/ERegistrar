package edu.mum.cs.cs425.labs.eregistrar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.cs.cs425.labs.eregistrar.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
