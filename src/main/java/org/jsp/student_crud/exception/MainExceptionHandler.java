package org.jsp.student_crud.exception;

import org.jsp.student_crud.helper.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MainExceptionHandler {
    @ExceptionHandler(DataShouldNotReapeatException.class)
    public ResponseEntity<ResponseStructure<String>> handle(DataShouldNotReapeatException exception) {
        ResponseStructure<String> structure = new ResponseStructure<String>();
        structure.setMessage(exception.getMessege());
        structure.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        structure.setData("Data not saved");
        return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handle(DataNotFoundException exception) {
        ResponseStructure<String> structure = new ResponseStructure<String>();
        structure.setMessage(exception.getMessege());
        structure.setStatus(HttpStatus.NOT_FOUND.value());
        structure.setData("Data not found");
        return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
    }
}
