package vn.yenthan.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import vn.yenthan.core.model.ErrorResponse;
import vn.yenthan.core.util.ResponseUtil;
import vn.yenthan.exception.payload.DataNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> errors.add(error.getDefaultMessage())
        );

        return ResponseUtil.err(errors.toString(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException ex) {

        return ResponseUtil.err(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> errors = new ArrayList<>();
        ex.getConstraintViolations().forEach(violation -> {
            errors.add(violation.getPropertyPath() + ": " + violation.getMessage());
        });

        return ResponseUtil.err(errors.toString(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler({NoSuchElementException.class, EntityNotFoundException.class, DataNotFoundException.class})
    @ResponseBody
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    protected ErrorResponse handleNotFoundException(Exception ex) {
        log.error(ex.getMessage());
        return ResponseUtil.err("Resource not found: " + ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CONFLICT)
    protected ErrorResponse handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        log.error(ex.getMessage());
        return ResponseUtil.err("Data conflict: " + ex.getMessage(), HttpStatus.CONFLICT.value());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
    protected ErrorResponse handleMethodNotAllowedException(HttpRequestMethodNotSupportedException ex) {
        log.error(ex.getMessage());
        return ResponseUtil.err("Method not allowed: " + ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED.value());
    }
}
