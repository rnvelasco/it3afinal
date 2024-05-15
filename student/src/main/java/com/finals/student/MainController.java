package com.finals.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@Controller
@RequestMapping(path="/final/student") // Updated URI
public class MainController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    public ResponseEntity createStudent(@RequestBody Student student) throws URISyntaxException {
        Student savedStudent = studentRepository.save(student);
        return ResponseEntity.created(new URI("/final/student/" + savedStudent.getId())).body(savedStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        Student currentStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No student found"));
        currentStudent = studentRepository.save(student);
        return ResponseEntity.ok("Student with id " + id + " updated.");
    }

    @GetMapping
    public @ResponseBody Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody ResponseEntity<?> getStudent(@PathVariable Integer id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        } else {
            return ResponseEntity.badRequest().body("No student found");
        }
    }

    @DeleteMapping(path="/{id}")
    public @ResponseBody ResponseEntity<?> deleteStudent(@PathVariable Integer id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.deleteById(id);
            return ResponseEntity.ok("Student with id " + id + " deleted.");
        } else {
            return ResponseEntity.badRequest().body("No student found");
        }
    }

    @PostMapping(path="/new") // Modified URI
    public ResponseEntity<?> addNewStudent(@RequestParam String name,
                                           @RequestParam String surname,
                                           @RequestParam String course,
                                           @RequestParam String email) {
        Student student = new Student(name, surname, course, email);
        Student savedStudent = studentRepository.save(student);
        return ResponseEntity.ok("New student with id " + savedStudent.getId() + " added.");
    }
}