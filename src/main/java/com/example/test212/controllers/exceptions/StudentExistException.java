package com.example.test212.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Student Already exist")  // 404
public class StudentExistException extends Exception{
}
