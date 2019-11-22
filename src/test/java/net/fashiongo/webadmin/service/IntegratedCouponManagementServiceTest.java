package net.fashiongo.webadmin.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.common.data.enums.coupon.CouponRegisterType;
import net.fashiongo.common.data.model.entity.coupon.CCoupon;
import net.fashiongo.common.data.model.entity.coupon.CCouponCode;
import net.fashiongo.common.data.model.entity.coupon.CCouponCondition;
import net.fashiongo.common.data.model.entity.coupon.CCouponStatistics;
import net.fashiongo.common.data.repository.coupon.CouponCodeRepository;
import net.fashiongo.common.data.repository.coupon.CouponConditionRepository;
import net.fashiongo.common.data.repository.coupon.CouponRepository;
import net.fashiongo.common.data.repository.coupon.CouponStatisticsRepository;
import net.fashiongo.webadmin.model.pojo.common.PagedResult;
import net.fashiongo.webadmin.model.primary.coupon.command.CouponQueryParam;
import net.fashiongo.webadmin.model.primary.coupon.dto.CouponDto;
import net.fashiongo.webadmin.model.primary.coupon.dto.CouponStatisticsDto;

/**
 * Created by Nicole Lee 9/11/2019
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class IntegratedCouponManagementServiceTest {

    @Resource(name = "data.couponRepository")
    private CouponRepository couponRepository;

    @Resource(name = "data.couponConditionRepository")
    private CouponConditionRepository couponConditionRepository;

    @Resource(name = "data.couponCodeRepository")
    private CouponCodeRepository couponCodeRepository;
    
    @Resource(name = "data.couponStatisticsRepository")
    private CouponStatisticsRepository couponStatisticsRepository;
    
    @Ignore
    @Test
    public void getCoupon() {

        Integer pn = 1;
        Integer ps = 20;

        CouponQueryParam queryParam = new CouponQueryParam();
        queryParam.setPn(pn);
        queryParam.setPs(ps);

        PagedResult<CouponDto> results = new PagedResult<>();

        List<CCoupon> couponEntityList = couponRepository.getCoupons(CouponRegisterType.FG.getValue(), PageRequest.of(pn - 1, ps));

        log.info("couponEntityList start ------------------------------------------------------");
        log.info("couponEntityList.size() : {}", couponEntityList.size());
        couponEntityList.forEach((x) -> log.info("couponEntityList : {}, {}", 
        		x.getId(),
        		x.getCouponName(),
        		x.getIssueStartDate(),
        		x.getIssueEndDate(),
        		x.getValidDurationDays()
        		));
        
        Assert.assertNotNull(couponEntityList);
        Assert.assertNotEquals(0, couponEntityList.size());

    }
    
    @Ignore
    @Test
    public void getCouponStatistics() {

        Integer pn = 1;
        Integer ps = 20;

        CouponQueryParam queryParam = new CouponQueryParam();
        queryParam.setPn(pn);
        queryParam.setPs(ps);

        PagedResult<CouponStatisticsDto> results = new PagedResult<>();

        List<CCouponStatistics> couponStatisticsEntityList = couponStatisticsRepository.getCouponStatistics(PageRequest.of(pn - 1, ps));

        List<CCoupon> couponEntityList = couponRepository.getCoupons(CouponRegisterType.FG.getValue(), PageRequest.of(pn - 1, ps));

        List<Long> couponIdList = couponEntityList.stream().map(CCoupon::getId).collect(Collectors.toList());

        List<CCouponCondition> couponConditionEntityList = couponConditionRepository.findByIsDeletedAndCouponIdIn(false, couponIdList);
        List<CCouponCode> couponCodeEntityList = couponCodeRepository.findByIsDeletedAndCouponIdIn(false, couponIdList);
        results.setRecords(CouponStatisticsDto.build(couponStatisticsEntityList, couponEntityList, couponConditionEntityList, couponCodeEntityList));

        long couponStatisticsCount = couponStatisticsRepository.getCouponStatCount();

        log.info("couponEntityList start ------------------------------------------------------");
        log.info("couponEntityList.size() : {}", couponEntityList.size());
        couponEntityList.forEach((x) -> log.info("couponEntityList : {}, {}", 
        		x.getId(),
        		x.getCouponName()
        		));

        log.info("couponConditionEntityList start ---------------------------------------------");
        log.info("couponConditionEntityList.size() : {}", couponConditionEntityList.size());
        couponConditionEntityList.forEach((x) -> log.info("couponConditionEntityList : {}, {}", 
        		x.getId(),
        		x.getDiscountType()
        		));

        log.info("couponStatisticsEntityList start --------------------------------------------");
        log.info("couponStatisticsEntityList.size() : {}", results.getRecords().size());
        log.info("couponStatisticsCount : {}", couponStatisticsCount);
        results.getRecords().forEach((x) -> log.info("couponStatisticsEntityList : {}, {}, {}, {}, {}, {}, {}, {}, {}", 
        		x.getBatchDate(),  
        		x.getCouponId(),  
        		x.getIssuedCouponCount(),
        		x.getUsedCouponCount(),
        		x.getTotalOrderCount(),
        		x.getTotalOrderAmount(),
        		x.getTotalVendorDiscountAmount(),
        		x.getTotalUserCount()
        		));
        
        Assert.assertNotNull(results);
        Assert.assertNotEquals(0, results.getTotal());

    }

}
