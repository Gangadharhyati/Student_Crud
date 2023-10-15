package org.jsp.student_crud.controller;

import java.util.List;

import org.jsp.student_crud.dto.Student;
import org.jsp.student_crud.helper.ResponseStructure;
import org.jsp.student_crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;
    @Autowired
    Student student;

    @PostMapping("/students")
    public ResponseEntity<ResponseStructure<Student>> save(@RequestBody Student student) {
        return new ResponseEntity<ResponseStructure<Student>>(studentService.save(student), HttpStatus.CREATED);
    }

    @PostMapping("/students/many")
    public ResponseEntity<ResponseStructure<List<Student>>> saveStudentMany(@RequestBody List<Student> student) {
        return new ResponseEntity<ResponseStructure<List<Student>>>(studentService.saveStudents(student),
                HttpStatus.CREATED);
    }

    @GetMapping("/students")
    @Operation()
    public ResponseEntity<ResponseStructure<List<Student>>> findAll() {
        return new ResponseEntity<ResponseStructure<List<Student>>>(studentService.findAll(),
                HttpStatus.FOUND);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<ResponseStructure<Student>> findById(@PathVariable int id) {
        return new ResponseEntity<ResponseStructure<Student>>(studentService.findById(id),
                HttpStatus.FOUND);
    }

    @GetMapping("/students/name")
    public ResponseEntity<ResponseStructure<List<Student>>> findByName(@RequestParam String name) {
        return new ResponseEntity<ResponseStructure<List<Student>>>(studentService.findByName(name),
                HttpStatus.FOUND);
    }

    @GetMapping("/students/mobile/{mobile}")
    public ResponseEntity<ResponseStructure<Student>> findByMobile(@PathVariable long mobile) {
        return new ResponseEntity<ResponseStructure<Student>>(studentService.findByMobile(mobile),
                HttpStatus.FOUND);
    }

    @GetMapping("/students/email/{email}")
    public ResponseEntity<ResponseStructure<Student>> findByEmail(@PathVariable String email) {
        return new ResponseEntity<ResponseStructure<Student>>(studentService.findByEmail(email),
                HttpStatus.FOUND);
    }

    @GetMapping("/students/percentage/greater/{percentage}")
    public ResponseEntity<ResponseStructure<List<Student>>> findByPercentageGreater(@PathVariable double percentage) {
        return new ResponseEntity<ResponseStructure<List<Student>>>(
                studentService.findByPercentageGreaterThan(percentage), HttpStatus.FOUND);
    }

    @GetMapping("/students/percentage/lesser/{percentage}")
    public ResponseEntity<ResponseStructure<List<Student>>> findByPercentageLessThan(@PathVariable double percentage) {
        return new ResponseEntity<ResponseStructure<List<Student>>>(studentService.findByPercentageLessThan(percentage),
                HttpStatus.FOUND);
    }

    @GetMapping("/students/result/{result}")
    public ResponseEntity<ResponseStructure<List<Student>>> findByResult(@PathVariable String result) {
        return new ResponseEntity<ResponseStructure<List<Student>>>(studentService.findByResult(result),
                HttpStatus.FOUND);
    }

    @GetMapping("/students/percentage/maths/english/greater/{marks}")
    public ResponseEntity<ResponseStructure<List<Student>>> findByMathsEnglishGreater(@PathVariable int marks) {
        return new ResponseEntity<ResponseStructure<List<Student>>>(studentService.findByMathsEnglishGreater(marks),
                HttpStatus.FOUND);
    }

    @DeleteMapping("/students/delete/id/{id}")
    public ResponseEntity<ResponseStructure<Student>> deleteById(@PathVariable int id) {
        return new ResponseEntity<ResponseStructure<Student>>(studentService.deleteById(id), HttpStatus.GONE);
    }

    @PutMapping("/students")
    public ResponseEntity<ResponseStructure<Student>> update(@RequestBody Student student) {
        return new ResponseEntity<ResponseStructure<Student>>(studentService.update(student), HttpStatus.OK);
    }
}
