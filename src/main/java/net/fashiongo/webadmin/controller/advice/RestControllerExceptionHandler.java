package net.fashiongo.webadmin.controller.advice;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.controller.MessageController;
import net.fashiongo.webadmin.controller.StaticController;
import net.fashiongo.webadmin.controller.StaticKpiController;
import net.fashiongo.webadmin.controller.WAPaymentController;
import net.fashiongo.webadmin.controller.sitemgmt.SitemgmtController;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice(assignableTypes = {WAPaymentController.class, MessageController.class, StaticController.class,
        StaticKpiController.class, SitemgmtController.class})
public class RestControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException: ", ex);
        return new ResponseEntity<>(JsonResponse.fail("Bad Request: " + ex.getBindingResult().getFieldErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(" "))), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Object> handleBindException(BindException ex) {
        log.error("BindException: ", ex);
        return new ResponseEntity<>(JsonResponse.fail("Bad Request: " + ex.getBindingResult().getFieldErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(" "))), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UncategorizedSQLException.class)
    public ResponseEntity<Object> handleUncategorizedSQLException(UncategorizedSQLException ex) {
        log.error("UncategorizedSQLException: ", ex);
        return new ResponseEntity<>(JsonResponse.fail("Query Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    public ResponseEntity<Object> handleBadSqlGrammarException(BadSqlGrammarException ex) {
        log.error("BadSqlGrammarException: ", ex);
        return new ResponseEntity<>(JsonResponse.fail("Query Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error("HttpMessageNotReadableException: ", ex);
        return new ResponseEntity<>(JsonResponse.fail("Request Message Not Readable"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        log.error("Exception: ", ex);
        return new ResponseEntity<>(JsonResponse.fail("Unknown Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
