package com.modak.elearning.shared;

import com.modak.elearning.exception.BadRequestException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ResponseEntityExceptionController {
 
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ErrorMessage errorUsuarioNoValido(BadRequestException e){
        return ErrorMessage.builder()
                            .message(e.getMessage())
                            .build();
    }
}
