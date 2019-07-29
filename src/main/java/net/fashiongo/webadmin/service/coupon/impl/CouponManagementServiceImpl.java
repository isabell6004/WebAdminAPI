package net.fashiongo.webadmin.service.coupon.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.common.data.enums.coupon.CouponRegisterType;
import net.fashiongo.common.data.enums.security.ResourceAuthorityType;
import net.fashiongo.common.data.model.entity.coupon.*;
import net.fashiongo.common.data.repository.coupon.*;
import net.fashiongo.webadmin.aop.CouponActionAuthorityCheck;
import net.fashiongo.webadmin.exception.coupon.*;
import net.fashiongo.webadmin.mapper.CouponMapper;
import net.fashiongo.webadmin.model.pojo.common.PagedResult;
import net.fashiongo.webadmin.model.pojo.common.SingleValueResult;
import net.fashiongo.webadmin.model.primary.coupon.command.*;
import net.fashiongo.webadmin.model.primary.coupon.dto.*;
import net.fashiongo.webadmin.service.SecurityGroupService;
import net.fashiongo.webadmin.service.coupon.CouponManagementService;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CouponManagementServiceImpl implements CouponManagementService {

    @Resource(name = "data.couponRepository")
    private CouponRepository couponRepository;

    @Resource(name = "data.couponHistoryRepository")
    private CouponHistoryRepository couponHistoryRepository;

    @Resource(name = "data.couponConditionRepository")
    private CouponConditionRepository couponConditionRepository;

    @Resource(name = "data.couponConditionHistoryRepository")
    private CouponConditionHistoryRepository couponConditionHistoryRepository;

    @Resource(name = "data.couponCodeRepository")
    private CouponCodeRepository couponCodeRepository;

    @Resource(name = "data.couponNotificationRepository")
    private CouponNotificationRepository couponNotificationRepository;

    @Resource(name = "data.couponBuyerGroupRepository")
    private CouponBuyerGroupRepository couponBuyerGroupRepository;

    @Resource(name = "data.couponVendorGroupRepository")
    private CouponVendorGroupRepository couponVendorGroupRepository;

    @Resource(name = "data.couponCommonRepository")
    private CouponCommonRepository couponCommonRepository;

    @Autowired
    private SecurityGroupService securityGroupService;

    private final CouponMapper couponMapper;


    private static final String[] UPPER_COMMON_CODES = {
            "coupon_type",
            "discount_base_type",
            "discount_type",
            "sale_type",
            "generate_type",
            "notification_method"
    };

    @Override
    @CouponActionAuthorityCheck(ResourceAuthorityType.Constants.VIEW)
    @Transactional
    public PagedResult<CouponDto> getCoupons(int pn, int ps) {

//        hasCouponActionAuthority(ResourceAuthorityType.VIEW.getValue());

        PagedResult<CouponDto> result = new PagedResult<>();

        List<CCoupon> couponEntityList = couponRepository.getCoupons(CouponRegisterType.FG.getValue(), PageRequest.of(pn - 1, ps));

        if (CollectionUtils.isEmpty(couponEntityList)) {
            return result;
        }

        List<Long> couponIdList = couponEntityList.stream().map(CCoupon::getId).collect(Collectors.toList());

        List<CCouponCondition> couponConditionEntityList = couponConditionRepository.findByIsDeletedAndCouponIdIn(false, couponIdList);
        List<CCouponCode> couponCodeEntityList = couponCodeRepository.findByIsDeletedAndCouponIdIn(false, couponIdList);
        result.setRecords(CouponDto.build(couponEntityList, couponConditionEntityList, couponCodeEntityList));

        long couponCount = couponRepository.getCouponCount(CouponRegisterType.FG.getValue());
        SingleValueResult total = new SingleValueResult();
        total.setTotalCount((int)couponCount);

        result.setTotal(total);
        result.setPageNumber(pn);
        return result;
    }

    @Override
    @CouponActionAuthorityCheck(ResourceAuthorityType.Constants.ADD)
    @Transactional
    public CouponDto createCoupon(CouponCreateInput createInput) {

        LocalDateTime now = LocalDateTime.now();
        createInput.setCreatedOn(now);
        createInput.setCreatedBy(Utility.getUsername());
        createInput.setModifiedOn(now);
        createInput.setModifiedBy(Utility.getUsername());
        createInput.validateCouponCreateInput();

        CCoupon couponEntity = couponMapper.toCoupon(createInput);
        couponRepository.save(couponEntity);

        CCouponCondition couponConditionEntity = createCouponCondition(createInput.getCouponCondition(), couponEntity.getId(), now);

        CCouponCode couponCodeEntity = null;
        if (couponEntity.getIsCodeUsed() && createInput.getCouponCode() != null) {
            couponCodeEntity = createCouponCode(createInput.getCouponCode(), couponEntity.getId(), now);
        }

        return CouponDto.build(couponEntity, couponConditionEntity, couponCodeEntity);
    }

    private CCouponCondition createCouponCondition(CouponConditionCreateInput createInput, Long couponId, LocalDateTime now) {

        createInput.setCouponId(couponId);
        createInput.setCreatedOn(now);
        createInput.setCreatedBy(Utility.getUsername());
        createInput.setModifiedOn(now);
        createInput.setModifiedBy(Utility.getUsername());
        createInput.validateCouponConditionRequest();
        CCouponCondition couponConditionEntity = couponMapper.toCouponCondition(createInput);

        couponConditionRepository.save(couponConditionEntity);

        return couponConditionEntity;
    }

    private CCouponCode createCouponCode(CouponCodeCreateInput createInput, Long couponId, LocalDateTime now) {

        if (!checkCouponCodeUnique(createInput.getCouponCode(), couponId)) {
            throw new NonUniqueCouponCodeException(createInput.getCouponCode() + ", coupon code is not unique. ");
        }

        createInput.setCouponId(couponId);
        createInput.setCreatedOn(now);
        createInput.setCreatedBy(Utility.getUsername());
        createInput.setModifiedOn(now);
        createInput.setModifiedBy(Utility.getUsername());
        CCouponCode couponCodeEntity = couponMapper.toCouponCode(createInput);

        couponCodeRepository.save(couponCodeEntity);

        return couponCodeEntity;
    }

    @Override
    @CouponActionAuthorityCheck(ResourceAuthorityType.Constants.ADD)
    @Transactional
    public CouponDto updateCoupon(Long couponId, CouponUpdateInput updateInput) {

        updateInput.validateCouponRequest();

        Optional<CCoupon> optionalCCoupon = couponRepository.findById(couponId);
        CCoupon couponEntity = optionalCCoupon.orElseThrow(() -> new NotFoundCouponException("cannot find coupon: " + couponId));

        checkCouponDeletionStatus(couponEntity.getIsDeleted(), couponId);
        checkCouponActivationStatus(couponEntity.getIsActive(), couponId);
        if (!couponEntity.getIsActive()) {
            checkCouponActionDueDate(couponEntity.getStartDate(), "edit", couponId);
        }

        Boolean previousIsNotifiedValue = couponEntity.getIsNotified();

        LocalDateTime now = LocalDateTime.now();
        updateInput.setModifiedOn(now);
        updateInput.setModifiedBy(Utility.getUsername());
        couponMapper.updateCoupon(updateInput, couponEntity);

        couponRepository.save(couponEntity);

        CCouponCondition couponConditionEntity = updateCouponCondition(updateInput.getCouponCondition(), now);
        CCouponCode couponCodeEntity = updateCouponCode(updateInput.getCouponCode(), now);

        if (previousIsNotifiedValue && !couponEntity.getIsNotified()) {
            deleteCouponNotification(couponId);
        }

        return CouponDto.build(couponEntity, couponConditionEntity, couponCodeEntity);
    }

    private CCouponCondition updateCouponCondition(CouponConditionUpdateInput updateInput, LocalDateTime now) {

        Optional<CCouponCondition> optionalCCouponCondition = couponConditionRepository.findById(updateInput.getId());
        CCouponCondition couponConditionEntity = optionalCCouponCondition.orElseThrow(() -> new NotFoundCouponException("cannot find coupon condition: " + updateInput.getId()));

        createCouponConditionHistory(couponConditionEntity);

        updateInput.setModifiedOn(now);
        updateInput.setModifiedBy(Utility.getUsername());
        couponMapper.updateCouponCondition(updateInput, couponConditionEntity);

        couponConditionRepository.save(couponConditionEntity);

        return couponConditionEntity;
    }

    private CCouponCode updateCouponCode(CouponCodeUpdateInput updateInput, LocalDateTime now) {

        Optional<CCouponCode> optionalCCouponCode = couponCodeRepository.findById(updateInput.getId());
        CCouponCode couponCodeEntity = optionalCCouponCode.orElseThrow(() -> new NotFoundCouponException("cannot find coupon code: " + updateInput.getId()));

        updateInput.setModifiedOn(now);
        updateInput.setModifiedBy(Utility.getUsername());
        couponMapper.updateCouponCode(updateInput, couponCodeEntity);

        if (!updateInput.getIsDeleted()) {
            checkCouponCodeUnique(updateInput.getCouponCode(), updateInput.getId());
        } else {
            couponCodeEntity.setIsDeleted(true);
            couponCodeEntity.setDeletedOn(now);
        }

        couponCodeRepository.save(couponCodeEntity);

        return couponCodeEntity.getIsDeleted() ? null : couponCodeEntity;
    }

    @Override
    @CouponActionAuthorityCheck(ResourceAuthorityType.Constants.EDIT)
    @Transactional
    public boolean activateCoupon(Long couponId) {

        Optional<CCoupon> optionalCCoupon = couponRepository.findById(couponId);
        CCoupon coupon = optionalCCoupon.orElseThrow(() -> new NotFoundCouponException("cannot find coupon: " + couponId));

        checkCouponDeletionStatus(coupon.getIsDeleted(), couponId);
        checkCouponPreviousActivationStatus(coupon.getActivatedOn(), couponId);
        checkCouponActionDueDate(coupon.getStartDate(), "activate", couponId);

        createCouponHistory(coupon);

        LocalDateTime now = LocalDateTime.now();
        coupon.setIsActive(true);
        coupon.setActivatedOn(now);
        coupon.setModifiedOn(LocalDateTime.now());
        coupon.setModifiedBy(Utility.getUsername());
        couponRepository.save(coupon);

        return true;
    }

    @Override
    @CouponActionAuthorityCheck(ResourceAuthorityType.Constants.EDIT)
    @Transactional
    public boolean deactivateCoupon(Long couponId) {

        Optional<CCoupon> optionalCCoupon = couponRepository.findById(couponId);
        CCoupon coupon = optionalCCoupon.orElseThrow(() -> new NotFoundCouponException("cannot find coupon: " + couponId));

        checkCouponDeletionStatus(coupon.getIsDeleted(), couponId);

        createCouponHistory(coupon);

        LocalDateTime now = LocalDateTime.now();
        coupon.setIsActive(false);
        coupon.setModifiedOn(now);
        coupon.setModifiedBy(Utility.getUsername());
        couponRepository.save(coupon);

        return true;
    }

    @Override
    @CouponActionAuthorityCheck(ResourceAuthorityType.Constants.DELETE)
    @Transactional
    public boolean deleteCoupon(Long couponId) {

        Optional<CCoupon> optionalCCoupon = couponRepository.findById(couponId);
        CCoupon coupon = optionalCCoupon.orElseThrow(() -> new NotFoundCouponException("cannot find coupon: " + couponId));

        checkCouponDeletionStatus(coupon.getIsDeleted(), couponId);
        checkCouponActivationStatus(coupon.getIsActive(), couponId);

        LocalDateTime now = LocalDateTime.now();
        deleteCouponCode(couponId, now);
        deleteCouponCondition(couponId, now);

        createCouponHistory(coupon);

        coupon.setIsDeleted(true);
        coupon.setDeletedOn(now);
        coupon.setModifiedOn(now);
        coupon.setModifiedBy(Utility.getUsername());
        couponRepository.save(coupon);

        return true;
    }

    private void deleteCouponCondition(Long couponId, LocalDateTime now) {

        CCouponCondition couponConditionEntity = couponConditionRepository.findFirstByIsDeletedAndCouponId(false, couponId);

        if (couponConditionEntity == null) {
            return;
        }

        createCouponConditionHistory(couponConditionEntity);

        couponConditionEntity.setIsDeleted(true);
        couponConditionEntity.setDeletedOn(now);
        couponConditionEntity.setModifiedOn(now);
        couponConditionEntity.setModifiedBy(Utility.getUsername());

        couponConditionRepository.save(couponConditionEntity);
    }

    private void deleteCouponCode(Long couponId, LocalDateTime now) {

        CCouponCode couponCodeEntity = couponCodeRepository.findFirstByIsDeletedAndCouponId(false, couponId);

        if (couponCodeEntity == null) {
            return;
        }

        couponCodeEntity.setIsDeleted(true);
        couponCodeEntity.setDeletedOn(now);
        couponCodeEntity.setModifiedOn(now);
        couponCodeEntity.setModifiedBy(Utility.getUsername());

        couponCodeRepository.save(couponCodeEntity);
    }

    private void createCouponHistory(CCoupon coupon) {
        CCouponHistory couponHistory = CCouponHistory.build(coupon, Utility.getUsername());
        couponHistoryRepository.save(couponHistory);
    }

    private void createCouponConditionHistory(CCouponCondition couponCondition) {
        couponConditionHistoryRepository.save(CCouponConditionHistory.build(couponCondition, Utility.getUsername()));
    }

    @Override
    @CouponActionAuthorityCheck(ResourceAuthorityType.Constants.VIEW)
    public CouponNotificationDto getCouponNotifications(Long couponId) {
        return CouponNotificationDto.build(couponNotificationRepository.findFirstByCouponId(couponId));
    }

    @Override
    @CouponActionAuthorityCheck(ResourceAuthorityType.Constants.ADD)
    @Transactional
    public CouponNotificationDto createCouponNotification(Long couponId, CouponNotificationCommonInput couponNotificationCommonInput) {

        if (couponNotificationCommonInput.getNotification() == null) {
            throw new InvalidInputCouponException("invalid notification input.");
        }

        couponNotificationCommonInput.getNotification().setCouponId(couponId);
        CCouponNotification couponNotificationEntity = couponMapper.toCouponNotification(couponNotificationCommonInput.getNotification());

        LocalDateTime now = LocalDateTime.now();
        couponNotificationEntity.setCreatedOn(now);
        couponNotificationEntity.setCreatedBy(Utility.getUsername());
        couponNotificationEntity.setModifiedOn(now);
        couponNotificationEntity.setModifiedBy(Utility.getUsername());

        couponNotificationRepository.save(couponNotificationEntity);

        //TODO: call file upload api to upload target file
        //TODO: call file upload api to upload image file

        return CouponNotificationDto.build(couponNotificationEntity);
    }

    @Override
    @CouponActionAuthorityCheck(ResourceAuthorityType.Constants.ADD)
    @Transactional
    public CouponNotificationDto updateCouponNotification(Long couponId, Long couponNotificationId, CouponNotificationCommonInput couponNotificationCommonInput) {

        if (couponNotificationCommonInput.getNotification() == null) {
            throw new InvalidInputCouponException("invalid notification input.");
        }

        Optional<CCoupon> optionalCCoupon = couponRepository.findById(couponId);
        CCoupon couponEntity = optionalCCoupon.orElseThrow(() -> new NotFoundCouponException("cannot find coupon: " + couponId));

        if (couponEntity.getIsDeleted() || couponEntity.getActivatedOn() != null) {
            throw new NotAllowedCouponException("cannot update coupon notification. " + couponNotificationId);
        }

        Optional<CCouponNotification> optionalCCouponNotification = couponNotificationRepository.findById(couponNotificationId);
        CCouponNotification couponNotificationEntity = optionalCCouponNotification.orElseThrow(() -> new NotFoundCouponNotificationException("cannot find coupon notification: " + couponNotificationId));

        couponMapper.updateCouponNotification(couponNotificationCommonInput.getNotification(), couponNotificationEntity);
        couponNotificationEntity.setModifiedOn(LocalDateTime.now());
        couponNotificationEntity.setModifiedBy(Utility.getUsername());

        couponNotificationRepository.save(couponNotificationEntity);

        if (couponNotificationCommonInput.getTargetFile() != null) {
            //TODO: call file upload api to upload target file
        }

        if (couponNotificationCommonInput.getImageFile() != null) {
            //TODO: call file upload api to upload image file
        }

        return CouponNotificationDto.build(couponNotificationEntity);
    }

    @Override
    @CouponActionAuthorityCheck(ResourceAuthorityType.Constants.DELETE)
    @Transactional
    public boolean deleteCouponNotification(Long couponId, Long couponNotificationId) {

        Optional<CCoupon> optionalCCoupon = couponRepository.findById(couponId);
        CCoupon couponEntity = optionalCCoupon.orElseThrow(() -> new NotFoundCouponException("cannot find coupon: " + couponId));

        if (couponEntity.getActivatedOn() != null) {
            throw new NotAllowedCouponException("cannot delete coupon notification. " + couponNotificationId);
        }

        Optional<CCouponNotification> optionalCCouponNotification = couponNotificationRepository.findById(couponNotificationId);
        CCouponNotification couponNotificationEntity = optionalCCouponNotification.orElseThrow(() -> new NotFoundCouponNotificationException("cannot find coupon notification: " + couponNotificationId));
        if (couponNotificationEntity.getIsSent()) {
            throw new NotAllowedCouponException("cannot delete coupon notification. " + couponNotificationId);
        }

        couponNotificationRepository.delete(couponNotificationEntity);

        return true;
    }

    private void deleteCouponNotification(Long couponId) {

        CCouponNotification couponNotificationEntity = couponNotificationRepository.findFirstByCouponId(couponId);

        if (couponNotificationEntity != null) {
            couponNotificationRepository.delete(couponNotificationEntity);
        }
    }

    @Override
    public CouponOptionOutput getCouponOptions() {
        return CouponOptionOutput.build(CouponCommonCodeDto.build(couponCommonRepository.getCommonCodes(Arrays.asList(UPPER_COMMON_CODES))),
                CouponBuyerGroupDto.build(couponBuyerGroupRepository.findByIsActiveOrderByCouponBuyerGroupNameAsc(true)),
                CouponVendorGroupDto.build(couponVendorGroupRepository.findByIsActiveOrderByCouponVendorGroupNameAsc(true)));
    }

    @Override
    public void hasCouponActionAuthority(String action) throws NotAuthorizedCouponException {
        Integer userId = Utility.getUserInfo().getUserId();

        if (!securityGroupService.hasSecurityResourceActionAuthority(userId,"FG Coupon Management", action)) {
            throw new NotAuthorizedCouponException("not authorized to " + action);
        }
    }

    @Override
    public boolean checkCouponCodeUnique(String couponCode, Long couponCodeId) {

        if (couponCodeId == null) {
            if (!CollectionUtils.isEmpty(couponCodeRepository.findByIsDeletedAndCouponCode(false, couponCode))) {
                return false;
            }
        } else {
            if (!CollectionUtils.isEmpty(couponCodeRepository.findByIsDeletedAndCouponCodeAndCouponIdNot(false, couponCode, couponCodeId))) {
                return false;
            }
        }

        return true;
    }

    private void checkCouponDeletionStatus(Boolean isDeleted, Long couponId) {
        if (isDeleted) {
            throw new NotFoundCouponException("cannot find coupon: " + couponId);
        }
    }

    private void checkCouponActivationStatus(Boolean isActive, Long couponId) {
        if (isActive) {
            throw new NotAllowedCouponException("cannot modify coupon due to past due date. " + couponId);
        }
    }

    private void checkCouponActionDueDate(LocalDateTime targetDate, String action, Long couponId) {
        if (LocalDateTime.now().isAfter(targetDate)) {
            throw new NotAllowedCouponException("cannot " + action + " coupon due to past due date. " + couponId);
        }
    }

    private void checkCouponPreviousActivationStatus(LocalDateTime activatedOn, Long couponId) {
        if (activatedOn != null) {
            throw new NotAllowedCouponException("cannot reactivate coupon. " + couponId);
        }
    }
}
