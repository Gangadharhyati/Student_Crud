package org.jsp.student_crud.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DataNotFoundException extends RuntimeException {
    String messege = "Data Not Found";
}
