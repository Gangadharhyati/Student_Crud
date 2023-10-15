package org.jsp.student_crud.dao;

import java.util.List;

import org.jsp.student_crud.dto.Student;
import org.jsp.student_crud.exception.DataNotFoundException;
import org.jsp.student_crud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao {

    @Autowired
    StudentRepository studentRepository;

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> saveStudents(List<Student> students) {
        return studentRepository.saveAll(students);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(int id) {
        return studentRepository.findById(id).orElseThrow(() -> {
            throw new DataNotFoundException("Data not found with id " + id);
        });
    }

    public List<Student> findByName(String name) {
        return studentRepository.findByName(name);
    }

    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public Student findByMobile(long mobile) {
        return studentRepository.findByMobile(mobile);
    }

    public List<Student> findPercentageGreaterThan(double percentage) {
        return studentRepository.findByPercentageGreaterThanEqual(percentage);
    }

    public List<Student> findPercentageLessThan(double percentage) {
        return studentRepository.findByPercentageLessThanEqual(percentage);
    }

    public List<Student> findByResult(String result) {
        return studentRepository.findByResult(result);
    }

    public List<Student> findByMathsEnglishGreater(int marks) {
        return studentRepository.findByMathsEnglishGreater(marks);
    }

    public void deleteById(Student student) {
        studentRepository.delete(student);
    }

    public Student update(Student student) {
        return studentRepository.save(student);
    }

}
