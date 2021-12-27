package com.ApiEstudiantes.app.repository;



import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ApiEstudiantes.app.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

	List<Student> findByAge(Integer age);
	
	
	@Query(value = "SELECT  * FROM db_estudiantes.students  ORDER BY calification  DESC", nativeQuery = true)
	List<Student> findTopThree(PageRequest of);


	

}
