package com.ApiEstudiantes.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ApiEstudiantes.app.model.Student;
import com.ApiEstudiantes.app.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {
	
	 @Autowired
	private StudentService studentService;
	 
	 //Create
	 @PostMapping
	 public ResponseEntity<?> create (@RequestBody Student student){
		 return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(student));
	 }
	 
	 //Read
	 @GetMapping("/{id}")
	 public ResponseEntity<?> read(@PathVariable(value = "id") Integer studentId){
		 Optional<Student> oStudent = studentService.findById(studentId);
		 
		 if(!oStudent.isPresent()) {
			 return ResponseEntity.notFound().build();
		 }
		 return ResponseEntity.ok(oStudent);
	 }
	 
	 //Update
	 @PutMapping("/{id}")
	 public ResponseEntity<?> update (@RequestBody Student studentDetails, @PathVariable Integer studentId){
		 Optional<Student> student = studentService.findById(studentId);
		 if(!student.isPresent()) {
			 return ResponseEntity.notFound().build();
		 }
		 student.get().setName(studentDetails.getName());
		 student.get().setLastName(studentDetails.getLastName());
		 student.get().setEmail(studentDetails.getEmail());
		 student.get().setAvatar(studentDetails.getAvatar());
		 student.get().setAge(studentDetails.getAge());
		 
		 return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(student.get()));
	 }
	 
	 
	 //Delete
	 @DeleteMapping("/{id}")
	 public ResponseEntity<?> delete (@PathVariable(value = "id") Integer studentId ){
		 if(!studentService.findById(studentId).isPresent()) {
			 return ResponseEntity.notFound().build();
		 }
		 studentService.deleteById(studentId);
		 return ResponseEntity.ok().build();
		 
	 }
	 
	 
	 //Read all
	 
	 @GetMapping
	 public List<Student> readAll (){
		 List<Student> students = StreamSupport
				 .stream(studentService.findAll().spliterator(), false)
				 .collect(Collectors.toList());
		 
		 return students;
	 }

	 //encontrar por edad
	 
	 @GetMapping("/findByAge/{age}")
	 public List<Student> findByAge(@PathVariable Integer age){
		return studentService.findbyAge(age);
	 }
	 
	 //top 3 calificaciones 
	 @GetMapping("/top3califications")
	 public List<Student> findTopThree(){
		return studentService.findTopThree();
	 }

	 @GetMapping("/studentsOver18")
	 public List<Student> findOverEighteen(){
		return studentService.findOverEighteen();
	 }
}
