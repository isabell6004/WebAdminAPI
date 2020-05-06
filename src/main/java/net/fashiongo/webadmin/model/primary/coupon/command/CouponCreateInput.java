package net.fashiongo.webadmin.model.primary.coupon.command;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.fashiongo.webadmin.exception.coupon.InvalidInputCouponException;

import java.time.LocalDateTime;

@Data
public class CouponCreateInput extends CouponCommonInput {

    @NotNull
    private @Valid CouponConditionCreateInput couponCondition;
    private @Valid CouponCodeCreateInput couponCode;

    public void validateCouponCreateInput() {

        if (this.getStartDate() != null) {
            if (this.getStartDate().isBefore(LocalDateTime.now())) {
                throw new InvalidInputCouponException("startDate should be after today.");
            }
            if (this.getEndDate() != null && this.getStartDate().isAfter(this.getEndDate())) {
                throw new InvalidInputCouponException("endDate should be after startDate.");
            }
        }

        if (this.getIssueStartDate() != null) {
            if (this.getIssueStartDate().isBefore(LocalDateTime.now())) {
                throw new InvalidInputCouponException("issueStartDate should be after today.");
            }
            if (this.getIssueEndDate() != null && this.getIssueStartDate().isAfter(this.getIssueEndDate())) {
                throw new InvalidInputCouponException("issueEndDate should be after issueStartDate.");
            }
        }

        if (this.getIsCodeUsed() && this.couponCode == null) {
            throw new InvalidInputCouponException("No coupon code");
        }
    }
}
