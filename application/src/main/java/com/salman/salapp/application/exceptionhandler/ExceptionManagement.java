package com.salman.salapp.application.exceptionhandler;

import com.salman.salapp.library.exceptions.AppException;
import com.salman.salapp.library.exceptions.NullIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionManagement {

    @ExceptionHandler(NullIdException.class)
    public ResponseEntity<Object> getAppNullException(AppException e) {

//        System.out.println(e.getAppNullExceptiontFault());
        Map<String, Object> map = new HashMap<>();
        map.put("time", LocalDate.now());
        map.put("message", e.getFaultMessage());
        map.put("code", e.getExceptionCode());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(map);
    }
}
