package com.rltw.todo;

import com.rltw.todo.util.exception.EntityNotFoundException;
import com.rltw.todo.util.exception.UnauthorizedException;
import com.rltw.todo.util.model.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code= HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseError handleValidationException(MethodArgumentNotValidException ex, WebRequest request){
        var errors = ex.getFieldErrors();
        Map<String,String> fieldErrors = new HashMap<>();

        errors.forEach((fieldError)->{
            fieldErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
        });

        return new ResponseError("Invalid payload",fieldErrors);
    }


    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(code=HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseError handleUnauthorizedException(){
        return new ResponseError("Unauthorized");
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(code=HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseError handleNotFoundException(EntityNotFoundException ex){
        return new ResponseError(ex.getMessage());
    }
}
