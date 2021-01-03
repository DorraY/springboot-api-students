package com.projet.Controller;


import com.projet.Model.Student;
import com.projet.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "id" )
                                                           Integer id) throws ResourceNotFoundException {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No student with this id!" + id));
        return ResponseEntity.ok().body(student);
    }

    @PostMapping("/students")
    public Student createStudent(@Valid @RequestBody Student student) {
        return studentRepository.save(student);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") Integer id ,
                                                  @Valid @RequestBody Student studentDetails) throws ResourceNotFoundException {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No student for this id" + id));

        student.setLastname(studentDetails.getLastname());
        student.setName(studentDetails.getName());
        student.setSection(studentDetails.getSection());

        final Student updatedStudent = studentRepository.save(student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/students/{id}")
    public Map<String, Boolean> deleteStudent(@PathVariable Integer id) throws ResourceNotFoundException {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No student for this id" + id));
        studentRepository.delete(student);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
