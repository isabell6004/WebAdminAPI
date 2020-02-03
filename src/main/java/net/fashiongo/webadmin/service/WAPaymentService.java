package net.fashiongo.webadmin.service;

import net.fashiongo.webadmin.dao.primary.CardStatusRepository;
import net.fashiongo.webadmin.dao.primary.EntityActionLogRepository;
import net.fashiongo.webadmin.dao.primary.OrderPaymentStatusRepository;
import net.fashiongo.webadmin.dao.primary.PaymentCreditCardRepository;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.message.Total;
import net.fashiongo.webadmin.model.pojo.payment.*;
import net.fashiongo.webadmin.model.pojo.payment.parameter.*;
import net.fashiongo.webadmin.model.pojo.payment.response.*;
import net.fashiongo.webadmin.model.primary.*;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DAHYE
 */
@Service
public class WAPaymentService extends ApiService {

    @Autowired
    private OrderPaymentStatusRepository orderPaymentStatusRepository;
    @Autowired
    private EntityActionLogRepository entityActionLogRepository;
    @Autowired
    private PaymentCreditCardRepository paymentCreditCardRepository;
    @Autowired
    private CardStatusRepository cardStatusRepository;

    @SuppressWarnings("unchecked")
    public GetPaymentStatusSearchOptionResponse getPaymentStatusSearchOption() {
        GetPaymentStatusSearchOptionResponse result = new GetPaymentStatusSearchOptionResponse();
        String spName = "up_wa_pay_GetPaymentStatusSearchOption";
        List<Object> params = new ArrayList<>();
        List<Object> results = jdbcHelper.executeSP(spName, params, Vendor.class, PaymentStatus.class);
        result.setVendorList((List<Vendor>) results.get(0));
        result.setPaymentStatusList((List<PaymentStatus>) results.get(1));
        return result;
    }

    @SuppressWarnings("unchecked")
    @Deprecated
    public GetPaymentStatusListResponse getPaymentStatusList(GetPaymentStatusListParameter param) {
        GetPaymentStatusListResponse result = new GetPaymentStatusListResponse();
        String spName = "up_wa_pay_GetPaymentStatusList_v1";
        List<Object> params = new ArrayList<>();
        params.add(param.getPageNum());
        params.add(param.getPageSize());
        params.add(param.getWholeSalerID());
        params.add(param.getPaymentStatusID());
        params.add(param.getFromDate());
        params.add(param.getToDate());
        params.add(param.getPoNumber());
        params.add(param.getConsolidationID());
        params.add(param.getBuyerName());
        params.add(param.getTransactionType());
        params.add(param.getSearchSuccess());
        params.add(param.getOrderBy());
        List<Object> results = jdbcHelper.executeSP(spName, params, PaymentStatusList.class, net.fashiongo.webadmin.data.model.Total.class);
        result.setPaymentStatusList((List<PaymentStatusList>) results.get(0));
        result.setTotal((List<net.fashiongo.webadmin.data.model.Total>) results.get(1));
        return result;
    }

    @SuppressWarnings("unchecked")
    @Deprecated
    public GetPendingPaymentTransactionResponse getPendingPaymentTransaction(GetPendingPaymentTransactionParameter param) {
        GetPendingPaymentTransactionResponse result = new GetPendingPaymentTransactionResponse();
        String spName = "up_wa_pay_GetPendingPaymentTransaction";
        List<Object> params = new ArrayList<>();
        params.add(param.getCreditCardId());
        List<Object> results = jdbcHelper.executeSP(spName, params, CreditCardStatus.class, OrderPayment.class);
        result.setCreditCardStatusList((List<CreditCardStatus>) results.get(0));
        result.setOrderPaymentStatusList((List<OrderPayment>) results.get(1));
        return result;
    }

    @SuppressWarnings("unchecked")
    public List<CodeCreditCardType> getCreditCardType() {
        String spName = "up_wa_Pay_GetCreditCardType";
        List<Object> params = new ArrayList<>();
        List<Object> results = jdbcHelper.executeSP(spName, params, CodeCreditCardType.class);
        return (List<CodeCreditCardType>) results.get(0);
    }

    public List<CardStatus> getCreditCardStatus() {
        return cardStatusRepository.findAll();
    }

    @SuppressWarnings("unchecked")
    public GetAllSavedCreditCardInfoResponse getAllSavedCreditCardInfo(GetAllSavedCreditCardInfoParameter param) {
        GetAllSavedCreditCardInfoResponse result = new GetAllSavedCreditCardInfoResponse();
        String spName = "up_wa_Pay_GetCreditCardList";
        List<Object> params = new ArrayList<>();
        params.add(param.getPageNum());
        params.add(param.getPageSize());
        params.add(param.getCardID());
        params.add(param.getDefaultCard());
        params.add(param.getCardTypeID());
        params.add(param.getCardStatusID());
        params.add(param.getBillingID());
        params.add(param.getCreditCountry());
        params.add(param.getCreditState());
        params.add(param.getBuyer());
        params.add(param.getReferenceID());
        params.add(param.getOrderBy());
        params.add(param.getOrderGubn());
        List<Object> results = jdbcHelper.executeSP(spName, params, CreditCardInfo.class, TotalCount.class);
        result.setCreditCardInfo((List<CreditCardInfo>) results.get(0));
        result.setTotalList((List<TotalCount>) results.get(1));
        return result;
    }

    @Transactional("primaryTransactionManager")
    public ResultCode setRestorePendingPaymentTransaction(SetRestorePendingPaymentTransactionParameter param) {
        LocalDateTime modifiedOn = LocalDateTime.now();
        String sessionUserID = Utility.getUsername();

        for (PaymentStatusID item : param.getPaymentStatusID()) {
            OrderPaymentStatus a = orderPaymentStatusRepository.findOneByOrderPaymentStatusID(item.getOrderPaymentStatusID());
            if (a.getOrderPaymentStatusID() > 0) {
                a.setPaymentStatusID(item.getPaymentStatusID());
                a.setPrePaymentStatusID(item.getPrePaymentStatusID());
                a.setModifiedBy(sessionUserID);
                a.setModifiedOn(modifiedOn);
                orderPaymentStatusRepository.save(a);

                EntityActionLog b = new EntityActionLog();
                b.setEntityTypeID(1);
                b.setEntityID(a.getOrderPaymentStatusID());
                b.setActionID(7002);
                b.setActedOn(modifiedOn);
                b.setActedBy(sessionUserID);
                b.setRemark("order payment status restored");
                entityActionLogRepository.save(b);
            }
        }
        PaymentCreditCard c = paymentCreditCardRepository.findOneByCreditCardID(param.getCreditCardId());
        c.setCardStatusID(1);
        c.setModifiedBy(sessionUserID);
        c.setModifiedOn(modifiedOn);
        paymentCreditCardRepository.save(c);

        EntityActionLog d = new EntityActionLog();
        d.setEntityTypeID(1);
        d.setEntityID(param.getCreditCardId());
        d.setActionID(7001);
        d.setActedOn(modifiedOn);
        d.setActedBy(sessionUserID);
        d.setRemark("credit card status changed");
        entityActionLogRepository.save(d);

        return new ResultCode(true, 1, "Restore successfully!");
    }

    public GetPayoutHistoryResponse getPayoutHistory(GetPayoutHistoryParameter param) {
        GetPayoutHistoryResponse result = new GetPayoutHistoryResponse();
        String spName = "up_wa_pay_GetPayoutList";
        List<Object> params = new ArrayList<>();
        params.add(param.getPageNum());
        params.add(param.getPageSize());
        params.add(param.getWholesalerId());
        params.add(param.getFromDate());
        params.add(param.getToDate());
        params.add(param.getPayoutStatus());
        params.add(param.getPayoutSchedule());
        params.add(param.getOrderBy());
        List<Object> results = jdbcHelper.executeSP(spName, params, Total.class, PayoutHistory.class);
        result.setTotal((List<Total>) results.get(0));
        result.setPayoutList((List<PayoutHistory>) results.get(1));
        return result;
    }

}
