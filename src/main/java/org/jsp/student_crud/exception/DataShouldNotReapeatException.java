package org.jsp.student_crud.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class DataShouldNotReapeatException extends RuntimeException{
    String messege = "Data Should not repeate";
}
