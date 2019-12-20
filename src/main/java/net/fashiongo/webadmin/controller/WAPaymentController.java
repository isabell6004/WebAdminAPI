package net.fashiongo.webadmin.controller;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.payment.parameter.*;
import net.fashiongo.webadmin.model.pojo.payment.response.GetAllSavedCreditCardInfoResponse;
import net.fashiongo.webadmin.model.pojo.payment.response.GetPaymentStatusListResponse;
import net.fashiongo.webadmin.model.pojo.payment.response.GetPayoutHistoryResponse;
import net.fashiongo.webadmin.model.pojo.payment.response.GetPendingPaymentTransactionResponse;
import net.fashiongo.webadmin.model.primary.CardStatus;
import net.fashiongo.webadmin.model.primary.CodeCreditCardType;
import net.fashiongo.webadmin.service.WAPaymentService;
import net.fashiongo.webadmin.service.renewal.RenewalWAPaymentService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/payment", produces = "application/json")
public class WAPaymentController {

    @Autowired
    private WAPaymentService waPaymentService;

    @Autowired
    private RenewalWAPaymentService renewalWAPaymentService;

    @PostMapping(value = "getPaymentStatusSearchOption")
    public JsonResponse<net.fashiongo.webadmin.data.model.payment.response.GetPaymentStatusSearchOptionResponse> getPaymentStatusSearchOption() {
        net.fashiongo.webadmin.data.model.payment.response.GetPaymentStatusSearchOptionResponse paymentStatusSearchOption = renewalWAPaymentService.getPaymentStatusSearchOption();
        return new JsonResponse<>(true, null, 0, paymentStatusSearchOption);
    }

    @PostMapping(value = "getPaymentStatusList")
    public JsonResponse<GetPaymentStatusListResponse> getPaymentStatusList(
            @Valid @RequestBody GetPaymentStatusListParameter parameters) {
        GetPaymentStatusListResponse result = waPaymentService.getPaymentStatusList(parameters);
        return new JsonResponse<>(true, null, 0, result);
    }

    @PostMapping(value = "getpendingpaymenttransaction")
    public JsonResponse<GetPendingPaymentTransactionResponse> getPendingPaymentTransaction(
            @Valid @RequestBody GetPendingPaymentTransactionParameter parameters) {
        GetPendingPaymentTransactionResponse result = waPaymentService.getPendingPaymentTransaction(parameters);
        return new JsonResponse<>(true, null, 0, result);
    }

    @PostMapping(value = "getCreditCardType")
    public JsonResponse<List<CodeCreditCardType>> getCreditCardType() {
        List<CodeCreditCardType> result = waPaymentService.getCreditCardType();
        return new JsonResponse<>(true, null, 0, result);
    }

    @PostMapping(value = "getCreditCardStatus")
    public JsonResponse<List<CardStatus>> getCreditCardStatus() {
        List<CardStatus> result = waPaymentService.getCreditCardStatus();
        return new JsonResponse<>(true, null, 0, result);
    }

    @PostMapping(value = "getAllSavedCreditCardInfo")
    public JsonResponse<GetAllSavedCreditCardInfoResponse> getAllSavedCreditCardInfo(
            @Valid @RequestBody GetAllSavedCreditCardInfoParameter param) {
        GetAllSavedCreditCardInfoResponse result = waPaymentService.getAllSavedCreditCardInfo(param);
        return JsonResponse.success(result);
    }

    @PostMapping(value = "setrestorependingpaymenttransaction")
    public JsonResponse<String> setRestorePendingPaymentTransaction(
            @Valid @RequestBody SetRestorePendingPaymentTransactionParameter param) {
        ResultCode result = waPaymentService.setRestorePendingPaymentTransaction(param);
        return new JsonResponse<>(result.getSuccess(), result.getResultMsg(), result.getResultCode(), null);
    }

    @PostMapping(value = "getpayouthistory")
    public JsonResponse<GetPayoutHistoryResponse> getPayoutHistory(
            @Valid @RequestBody GetPayoutHistoryParameter param) {
        GetPayoutHistoryResponse result = waPaymentService.getPayoutHistory(param);
        return new JsonResponse<>(true, null, 0, result);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException: ", ex);
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
