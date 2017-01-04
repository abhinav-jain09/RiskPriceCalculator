package com.bold.risk.api.common;

import com.bold.risk.api.model.ValidationErrors;
import com.bold.risk.api.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrors handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return ValidationErrors.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .reason(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .fieldErrors(fieldErrors.stream().collect(Collectors.toMap(FieldError::getField, fieldError ->
                        ErrorMessage.builder()
                                .code(String.format("error.%s", fieldError.getDefaultMessage()))
                                .arguments(Arrays.stream(fieldError.getArguments()).skip(1).collect(Collectors.toList()))
                                .build())
                )).build();
    }
}
