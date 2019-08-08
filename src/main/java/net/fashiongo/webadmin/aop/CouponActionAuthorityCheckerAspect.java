package net.fashiongo.webadmin.aop;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.service.coupon.CouponManagementService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

@Aspect
@Configuration
@Slf4j
public class CouponActionAuthorityCheckerAspect {

    @Autowired
    private CouponManagementService couponManagementService;

    @Before("@annotation(CouponActionAuthorityCheck)")
    public void checkCouponActionAuthority(JoinPoint joinPoint) {
        log.debug("===== START ASPECT [Coupon Authority Check] =====");

        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        CouponActionAuthorityCheck couponAuthorityCheck = method.getAnnotation(CouponActionAuthorityCheck.class);
        String action = couponAuthorityCheck.value();

        couponManagementService.hasCouponActionAuthority(action);

        log.debug("===== END ASPECT [Coupon Authority Check] =====");
    }
}
