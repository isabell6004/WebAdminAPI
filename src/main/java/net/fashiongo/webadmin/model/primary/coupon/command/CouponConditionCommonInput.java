package net.fashiongo.webadmin.model.primary.coupon.command;

import lombok.*;
import net.fashiongo.common.data.enums.coupon.CouponDiscountType;
import net.fashiongo.common.data.model.entity.coupon.CCouponCondition;
import net.fashiongo.webadmin.exception.coupon.InvalidInputCouponException;
import net.fashiongo.webadmin.utility.Utility;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public abstract class CouponConditionCommonInput {

    private Long couponId;
    @NotNull
    private Integer discountType;
    private BigDecimal minSaleAmount;
    private BigDecimal maxSaleAmount;
    private Integer minSaleQty;
    private Integer maxSaleQty;
    private BigDecimal discountAmount;
    private Double discountRate;
    private BigDecimal maxDiscountAmount;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime modifiedOn;
    private String modifiedBy;

    public void updateCouponConditionEntity(CCouponCondition conditionEntity, LocalDateTime now) {

        conditionEntity.setDiscountType(this.discountType);
        conditionEntity.setMinSaleAmount(this.minSaleAmount);
        conditionEntity.setMaxSaleAmount(this.maxSaleAmount);
        conditionEntity.setMinSaleQty(this.minSaleQty);
        conditionEntity.setMaxSaleQty(this.maxSaleQty);
        conditionEntity.setDiscountAmount(this.discountAmount);
        conditionEntity.setDiscountRate(this.discountRate);
        conditionEntity.setMaxDiscountAmount(this.maxDiscountAmount);

        conditionEntity.setModifiedOn(now);
        conditionEntity.setModifiedBy(Utility.getUsername());
    }

    public void validateCouponConditionRequest() {

        if (this.getMinSaleAmount() != null &&
                this.getMaxSaleAmount() != null &&
                this.getMinSaleAmount().compareTo(this.getMaxSaleAmount()) == 1) {
            throw new InvalidInputCouponException("Invalid sale amounts");
        }

        if (this.getMinSaleQty() != null &&
                this.getMaxSaleQty() != null &&
                this.getMinSaleQty().compareTo(this.getMaxSaleQty()) == 1) {
            throw new InvalidInputCouponException("Invalid sale qty");
        }

        if (this.getDiscountType().equals(CouponDiscountType.AMOUNT.getValue()) && this.getDiscountAmount() == null) {
            throw new InvalidInputCouponException("No discount amount");
        }

        if (this.getDiscountType().equals(CouponDiscountType.RATE.getValue()) && this.getDiscountRate() == null) {
            throw new InvalidInputCouponException("No discount rate");
        }
    }
}
