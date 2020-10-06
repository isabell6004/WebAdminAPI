package net.fashiongo.webadmin.model.pojo.payment.parameter.mapper;

import net.fashiongo.webadmin.data.model.vendor.response.VendorBlockPayoutScheduleInfoResponse;
import net.fashiongo.webadmin.model.pojo.payment.parameter.PaymentScheduleInfo;

public class PaymentScheduleInfoMapper {

    public static PaymentScheduleInfo convert(Integer vendorid, VendorBlockPayoutScheduleInfoResponse info) {
        return PaymentScheduleInfo.builder()
                .wholesalerId(vendorid)
                .isLocked(false)
                .payoutScheduleId(info.getPayoutSchedule())
                .weekday(info.getWeekday())
                .dayOfMonth(info.getDayOfMonth())
                .build();
    }

    public static PaymentScheduleInfo convertLock(Integer vendorid) {
        return PaymentScheduleInfo.builder()
                .wholesalerId(vendorid)
                .isLocked(true)
                .payoutScheduleId(1)
                .build();
    }
}
