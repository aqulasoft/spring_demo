package com.example.test212.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Student isn't exist")  // 404
public class StudentNotExistException extends Exception{
}
