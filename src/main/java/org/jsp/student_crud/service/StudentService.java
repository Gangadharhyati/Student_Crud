package org.jsp.student_crud.service;

import java.util.ArrayList;
import java.util.List;

import org.jsp.student_crud.dao.StudentDao;
import org.jsp.student_crud.dto.Student;
import org.jsp.student_crud.exception.DataNotFoundException;
import org.jsp.student_crud.exception.DataShouldNotReapeatException;
import org.jsp.student_crud.helper.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentDao studentDao;

    public ResponseStructure<Student> save(Student student) {

        Student student1 = studentDao.findByMobile(student.getMobile());
        Student student2 = studentDao.findByEmail(student.getEmail());

        if (student1 == null && student2 == null) {
            int totalMarks = student.getEnglish() + student.getMaths() + student.getScience();
            double percentage = totalMarks / 3.0;
            student.setPercentage(percentage);
            if (percentage < 35 || student.getEnglish() < 35 || student.getMaths() < 35 || student.getScience() < 35) {
                student.setResult("fail");
            } else if (percentage > 35 && percentage < 60) {
                student.setResult("secondclass");
            } else if (percentage > 60 && percentage < 85) {
                student.setResult("firstclass");
            } else if (percentage >= 85) {
                student.setResult("distinction");
            }

            ResponseStructure<Student> structure = new ResponseStructure<>();
            structure.setData(studentDao.save(student));
            structure.setStatus(HttpStatus.CREATED.value());
            structure.setMessage("Student saved successfully");
            return structure;
        } else {
            if (student1 == null) {
                throw new DataShouldNotReapeatException("Email shold not be repeated:" + student.getEmail());
            } else if (student2 == null) {
                throw new DataShouldNotReapeatException("Mobile shold not be repeated:" + student.getMobile());
            } else {
                throw new DataShouldNotReapeatException(
                        "Email and Mobile shold not be repeated:" + student.getEmail() + " " + student.getMobile());
            }
        }
    }

    public ResponseStructure<List<Student>> saveStudents(List<Student> students) {

        List<Student> list = new ArrayList<Student>();
        for (Student student : students) {
            Student student1 = studentDao.findByMobile(student.getMobile());
            Student student2 = studentDao.findByEmail(student.getEmail());

            if (student1 == null && student2 == null) {
                list.add(student);
            }
        }
        if (list.isEmpty()) {
            throw new DataShouldNotReapeatException("All Data Already Exists");
        } else {
            // students = list;
            for (Student student : list) {
                int totalMarks = student.getEnglish() + student.getMaths() + student.getScience();
                double percentage = totalMarks / 3.0;
                student.setPercentage(percentage);
                if (percentage < 35 || student.getEnglish() < 35 || student.getMaths() < 35
                        || student.getScience() < 35) {
                    student.setResult("fail");
                } else if (percentage > 35 && percentage < 60) {
                    student.setResult("secondclass");
                } else if (percentage > 60 && percentage < 85) {
                    student.setResult("firstclass");
                } else if (percentage >= 85) {
                    student.setResult("distinction");
                }

            }
            ResponseStructure<List<Student>> structure = new ResponseStructure<>();
            if (list.size() != students.size())
                structure.setMessage(list.size() + "data saved" + (students.size() - list.size()) + "data not saved");
            else
                structure.setMessage("Student saved successfully");

            structure.setData(studentDao.saveStudents(list));
            structure.setStatus(HttpStatus.CREATED.value());
            return structure;

        }
    }

    public ResponseStructure<List<Student>> findAll() {
        List<Student> list = studentDao.findAll();
        if (list.isEmpty()) {
            throw new DataNotFoundException();
        } else {
            ResponseStructure<List<Student>> structure = new ResponseStructure<>();
            structure.setData(list);
            structure.setStatus(HttpStatus.FOUND.value());
            structure.setMessage("Data found successfully");
            return structure;
        }
    }

    public ResponseStructure<Student> findById(int id) {
        ResponseStructure<Student> structure = new ResponseStructure<>();
        structure.setData(studentDao.findById(id));
        structure.setStatus(HttpStatus.FOUND.value());
        structure.setMessage(" Data Found Successfully");
        return structure;
    }

    public ResponseStructure<List<Student>> findByName(String name) {
        List<Student> list = studentDao.findByName(name);
        if (list == null) {
            ResponseStructure<List<Student>> structure = new ResponseStructure<>();
            structure.setData(null);
            structure.setStatus(HttpStatus.NOT_FOUND.value());
            structure.setMessage("Data Not Found");
            return structure;
        }
        ResponseStructure<List<Student>> structure = new ResponseStructure<>();
        structure.setData(list);
        structure.setStatus(HttpStatus.FOUND.value());
        structure.setMessage(" Data Found Successfully");
        return structure;
    }

    public ResponseStructure<Student> findByMobile(long mobile) {

        Student student = studentDao.findByMobile(mobile);
        if (student == null)
            throw new DataNotFoundException("data not found with mobile" + mobile);

        ResponseStructure<Student> structure = new ResponseStructure<>();
        structure.setMessage(" Data Found Successfully");
        structure.setData(student);
        structure.setStatus(HttpStatus.FOUND.value());
        return structure;

    }

    public ResponseStructure<Student> findByEmail(String email) {
        Student student = studentDao.findByEmail(email);
        if (student == null)
            throw new DataNotFoundException("data not found with emai " + email);

        ResponseStructure<Student> structure = new ResponseStructure<>();
        structure.setMessage(" Data Found Successfully");
        structure.setData(student);
        structure.setStatus(HttpStatus.FOUND.value());
        return structure;

    }

    public ResponseStructure<List<Student>> findByPercentageGreaterThan(double percentage) {
        List<Student> list = studentDao.findPercentageGreaterThan(percentage);
        if (list.isEmpty()) {
            throw new DataNotFoundException(" Data Not Found for percentage greater than" + percentage);
        } else {
            ResponseStructure<List<Student>> structure = new ResponseStructure<>();
            structure.setData(list);
            structure.setStatus(HttpStatus.FOUND.value());
            structure.setMessage("Data found successfully");
            return structure;
        }
    }

    public ResponseStructure<List<Student>> findByPercentageLessThan(double percentage) {
        List<Student> list = studentDao.findPercentageLessThan(percentage);
        if (list.isEmpty()) {
            throw new DataNotFoundException("Data not found for percentage less than " + percentage);
        } else {
            ResponseStructure<List<Student>> structure = new ResponseStructure<>();
            structure.setData(list);
            structure.setStatus(HttpStatus.FOUND.value());
            structure.setMessage("Data found successfully");
            return structure;
        }
    }

    public ResponseStructure<List<Student>> findByResult(String result) {
        List<Student> list = studentDao.findByResult(result);
        if (list.isEmpty()) {
            throw new DataNotFoundException("Data not found for with" + result);
        } else {
            ResponseStructure<List<Student>> structure = new ResponseStructure<>();
            structure.setData(list);
            structure.setStatus(HttpStatus.FOUND.value());
            structure.setMessage("Data found successfully");
            return structure;
        }
    }

    public ResponseStructure<List<Student>> findByMathsEnglishGreater(int marks) {
        List<Student> list = studentDao.findByMathsEnglishGreater(marks);
        if (list.isEmpty()) {
            throw new DataNotFoundException(
                    "No Students Record, whose Marks in Maths and Science is Greater than" + marks);
        } else {
            ResponseStructure<List<Student>> structure = new ResponseStructure<>();
            structure.setData(list);
            structure.setStatus(HttpStatus.FOUND.value());
            structure.setMessage("Data found successfully");
            return structure;
        }
    }

    public ResponseStructure<Student> deleteById(int id) {
        ResponseStructure<Student> structure = new ResponseStructure<>();
        structure.setData(studentDao.findById(id));
        structure.setStatus(HttpStatus.GONE.value());
        structure.setMessage("Data deleted successfully with id" + id);

        studentDao.deleteById(studentDao.findById(id));
        return structure;
    }

    public ResponseStructure<Student> update(Student student) {

        studentDao.findById(student.getId());
        int totalMarks = student.getEnglish() + student.getMaths() + student.getScience();
        double percentage = totalMarks / 3.0;
        student.setPercentage(percentage);
        if (percentage < 35 || student.getEnglish() < 35 || student.getMaths() < 35 || student.getScience() < 35) {
            student.setResult("fail");
        } else if (percentage > 35 && percentage < 60) {
            student.setResult("secondclass");
        } else if (percentage > 60 && percentage < 85) {
            student.setResult("firstclass");
        } else if (percentage >= 85) {
            student.setResult("distinction");
        }

        ResponseStructure<Student> structure = new ResponseStructure<>();
        structure.setData(studentDao.update(student));
        structure.setStatus(HttpStatus.OK.value());
        structure.setMessage("Data Update Successfully" + student.getId());
        return structure;

    }

}
