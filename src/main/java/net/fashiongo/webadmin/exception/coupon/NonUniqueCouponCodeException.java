package net.fashiongo.webadmin.exception.coupon;

public class NonUniqueCouponCodeException extends RuntimeException {

    public NonUniqueCouponCodeException(String message) {
        super(message);
    }
}
