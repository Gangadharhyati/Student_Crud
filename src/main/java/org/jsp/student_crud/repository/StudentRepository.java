package org.jsp.student_crud.repository;

import java.util.List;

import org.jsp.student_crud.dto.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByName(String name);
    // Student findById(int id);

    Student findByEmail(String email);

    Student findByMobile(long mobile);

    List<Student> findByPercentageGreaterThanEqual(double percentage);

    List<Student> findByPercentageLessThanEqual(double percentage);

    List<Student> findByResult(String result);

    @Query("SELECT x FROM Student x WHERE maths >= ?1 and english >=1")
    List<Student> findByMathsEnglishGreater(int marks);

    // List<Student> findByMathsGreaterThanEqualAndEnglishGreaterThanEqual(int
    // marks1,int marks2);
}
