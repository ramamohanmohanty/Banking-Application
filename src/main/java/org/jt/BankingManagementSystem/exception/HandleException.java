package org.jt.BankingManagementSystem.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class HandleException {
    /*  @ResponseStatus(HttpStatus.BAD_REQUEST)
     @ExceptionHandler(RuntimeException.class)
     public Map<String, String> handel(RuntimeException e) {
         var map = new HashMap<String, String>();
         map.put("title", "NOT__FOUND");
         map.put("Dessage", "Internal_Server_Error");
         map.put("timestamp", LocalDateTime.now().toString());
         return map;
     }*/
    //Eception handel in Controller
    @ExceptionHandler(NoSuchElementException.class)
    public ProblemDetail handel(NoSuchElementException e) {
        return ProblemDetail
                .forStatusAndDetail(HttpStatusCode.valueOf(500),e.getMessage());
    }
    @ExceptionHandler(NoResourceFoundException.class)
    public ProblemDetail handel(NoResourceFoundException e) {
        return ProblemDetail
                .forStatusAndDetail(HttpStatusCode.valueOf(400),e.getMessage());
    }
    //Exception handel in Service
    //This is called at last but it is not recomended.
    @ExceptionHandler(RuntimeException.class)
    public ProblemDetail handel(RuntimeException e) {
        //change the type..
        var problemdetail=ProblemDetail.forStatus(HttpStatusCode.valueOf(400));
        problemdetail.setTitle("NOT__FOUND");
        problemdetail.setDetail(e.getMessage());
        return problemdetail;
    }
    @ExceptionHandler(Exception.class)
    public ProblemDetail handel(Exception e) {
        return ProblemDetail
                .forStatusAndDetail(HttpStatusCode.valueOf(500),e.getMessage());
    }
}
