package com.ApiEstudiantes.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ApiEstudiantes.app.model.Student;

@Service
public interface StudentService {
	

	public Iterable<Student> findAll();
	
	public Page<Student> findAll(Pageable pageable);
	
	public Optional<Student> findById(Integer id);
	
	public Student save(Student student);
	
	public void deleteById(Integer id);
	


	public List<Student> findbyAge(Integer age);
	
	public List<Student> findOverEighteen();
	
	public List<Student> findTopThree();
}
