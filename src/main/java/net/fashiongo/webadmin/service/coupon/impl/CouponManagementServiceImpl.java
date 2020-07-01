package net.fashiongo.webadmin.service.coupon.impl;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.common.data.enums.coupon.CouponRegisterType;
import net.fashiongo.common.data.enums.security.ResourceAuthorityType;
import net.fashiongo.common.data.model.entity.coupon.CCoupon;
import net.fashiongo.common.data.model.entity.coupon.CCouponCode;
import net.fashiongo.common.data.model.entity.coupon.CCouponCondition;
import net.fashiongo.common.data.model.entity.coupon.CCouponConditionHistory;
import net.fashiongo.common.data.model.entity.coupon.CCouponHistory;
import net.fashiongo.common.data.model.entity.coupon.CCouponNotification;
import net.fashiongo.common.data.model.entity.coupon.CCouponStatistics;
import net.fashiongo.common.data.repository.coupon.CouponBuyerGroupRepository;
import net.fashiongo.common.data.repository.coupon.CouponCodeRepository;
import net.fashiongo.common.data.repository.coupon.CouponCommonRepository;
import net.fashiongo.common.data.repository.coupon.CouponConditionHistoryRepository;
import net.fashiongo.common.data.repository.coupon.CouponConditionRepository;
import net.fashiongo.common.data.repository.coupon.CouponHistoryRepository;
import net.fashiongo.common.data.repository.coupon.CouponNotificationRepository;
import net.fashiongo.common.data.repository.coupon.CouponRepository;
import net.fashiongo.common.data.repository.coupon.CouponStatisticsRepository;
import net.fashiongo.common.data.repository.coupon.CouponVendorGroupRepository;
import net.fashiongo.webadmin.aop.CouponActionAuthorityCheck;
import net.fashiongo.webadmin.config.CouponStorageProperties;
import net.fashiongo.webadmin.exception.coupon.InvalidInputCouponException;
import net.fashiongo.webadmin.exception.coupon.NonUniqueCouponCodeException;
import net.fashiongo.webadmin.exception.coupon.NotAllowedCouponException;
import net.fashiongo.webadmin.exception.coupon.NotAuthorizedCouponException;
import net.fashiongo.webadmin.exception.coupon.NotFoundCouponException;
import net.fashiongo.webadmin.exception.coupon.NotFoundCouponNotificationException;
import net.fashiongo.webadmin.mapper.CouponMapper;
import net.fashiongo.webadmin.model.pojo.common.PagedResult;
import net.fashiongo.webadmin.model.pojo.common.SingleValueResult;
import net.fashiongo.webadmin.model.primary.coupon.command.CouponCodeCreateInput;
import net.fashiongo.webadmin.model.primary.coupon.command.CouponCodeUpdateInput;
import net.fashiongo.webadmin.model.primary.coupon.command.CouponConditionCreateInput;
import net.fashiongo.webadmin.model.primary.coupon.command.CouponConditionUpdateInput;
import net.fashiongo.webadmin.model.primary.coupon.command.CouponCreateInput;
import net.fashiongo.webadmin.model.primary.coupon.command.CouponNotificationInput;
import net.fashiongo.webadmin.model.primary.coupon.command.CouponOptionOutput;
import net.fashiongo.webadmin.model.primary.coupon.command.CouponUpdateInput;
import net.fashiongo.webadmin.model.primary.coupon.dto.CouponBuyerGroupDto;
import net.fashiongo.webadmin.model.primary.coupon.dto.CouponCommonCodeDto;
import net.fashiongo.webadmin.model.primary.coupon.dto.CouponDto;
import net.fashiongo.webadmin.model.primary.coupon.dto.CouponNotificationDto;
import net.fashiongo.webadmin.model.primary.coupon.dto.CouponStatisticsDto;
import net.fashiongo.webadmin.model.primary.coupon.dto.CouponVendorGroupDto;
import net.fashiongo.webadmin.service.SecurityGroupService;
import net.fashiongo.webadmin.service.coupon.CouponManagementService;
import net.fashiongo.webadmin.support.FileNameUtils;
import net.fashiongo.webadmin.support.storage.SwiftApiCallFactory;
import net.fashiongo.webadmin.utility.Utility;

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

    @Resource(name = "data.couponStatisticsRepository")
    private CouponStatisticsRepository couponStatisticsRepository;
    
    @Autowired
    @Qualifier("swiftApiCallFactory")
    private SwiftApiCallFactory factory;

    @Autowired
    private CouponStorageProperties properties;

    @Autowired
    private SecurityGroupService securityGroupService;

    private final CouponMapper couponMapper;

    @Value("${coupon.notification.image-root}")
    private String imageRootUrl;


    private static final String[] UPPER_COMMON_CODES = {
            "coupon_type",
            "discount_base_type", 
            "discount_type",
            "sale_type",
            "generate_type",
            "notification_method",
            "issue_method"
    };

    @Override
    @CouponActionAuthorityCheck(ResourceAuthorityType.Constants.VIEW)
    @Transactional
    public PagedResult<CouponDto> getCoupons(int pn, int ps) {

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
        CCouponCode couponCodeEntity = couponEntity.getIsCodeUsed() ? createCouponCode(createInput.getCouponCode(), couponEntity.getId(), now) : null;

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

        if (createInput == null || createInput.getCouponCode() == null) {
            return null;
        }

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

        Boolean previousIsNotifiedValue = couponEntity.getIsNotified();

        LocalDateTime now = LocalDateTime.now();
        updateInput.setModifiedOn(now);
        updateInput.setModifiedBy(Utility.getUsername());
        couponMapper.updateCoupon(updateInput, couponEntity);

        couponRepository.save(couponEntity);

        CCouponCondition couponConditionEntity = updateCouponCondition(updateInput.getCouponCondition(), now);
        CCouponCode couponCodeEntity = updateCouponCode(updateInput.getCouponCode(), couponId, now);

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

    private CCouponCode updateCouponCode(CouponCodeUpdateInput updateInput, Long couponId, LocalDateTime now) {

        if (updateInput == null) {
            return null;
        }

        if (updateInput.getId() == null) {
            return createCouponCode(couponMapper.toCouponCodeCreateInput(updateInput), couponId, now);
        }

        Optional<CCouponCode> optionalCCouponCode = couponCodeRepository.findById(updateInput.getId());
        CCouponCode couponCodeEntity = optionalCCouponCode.orElseThrow(() -> new NotFoundCouponException("cannot find coupon code: " + updateInput.getId()));

        updateInput.setModifiedOn(now);
        updateInput.setModifiedBy(Utility.getUsername());
        couponMapper.updateCouponCode(updateInput, couponCodeEntity);

        if (!updateInput.getIsDeleted()) {
            if (!checkCouponCodeUnique(updateInput.getCouponCode(), updateInput.getId())) {
                throw new NonUniqueCouponCodeException("coupon code not unique. " + updateInput.getCouponCode());
            }
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
        checkCouponActionDueDate(coupon.getIssueStartDate(), "activate", couponId);
        checkCouponActionDueDate(coupon.getStartDate(), "activate", couponId);

        createCouponHistory(coupon);

        LocalDateTime now = LocalDateTime.now();
        coupon.setIsActive(true);
        coupon.setActivatedOn(now);
        coupon.setActivatedBy(Utility.getUsername());
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
        return CouponNotificationDto.build(couponNotificationRepository.findFirstByCouponId(couponId), imageRootUrl);
    }

    @Override
    @CouponActionAuthorityCheck(ResourceAuthorityType.Constants.ADD)
    @Transactional
    public CouponNotificationDto createCouponNotification(Long couponId,
                                                          CouponNotificationInput couponNotificationInput,
                                                          MultipartFile targetFile,
                                                          MultipartFile imageFile) throws IOException {

        if (couponNotificationInput == null || targetFile.isEmpty() || imageFile.isEmpty()) {
            throw new InvalidInputCouponException("invalid notification input.");
        }

        Optional<CCoupon> optionalCCoupon = couponRepository.findById(couponId);
        CCoupon couponEntity = optionalCCoupon.orElseThrow(() -> new NotFoundCouponException("cannot find coupon: " + couponId));

        couponNotificationInput.setCouponId(couponId);
        CCouponNotification couponNotificationEntity = couponMapper.toCouponNotification(couponNotificationInput);

        LocalDateTime now = LocalDateTime.now();
        couponNotificationEntity.setIsSent(false);
        couponNotificationEntity.setCreatedOn(now);
        couponNotificationEntity.setCreatedBy(Utility.getUsername());
        couponNotificationEntity.setModifiedOn(now);
        couponNotificationEntity.setModifiedBy(Utility.getUsername());
        couponNotificationRepository.save(couponNotificationEntity);

        String originalTargetFileName = targetFile.getOriginalFilename();
        InputStream targetFileInputStream = targetFile.getInputStream();
        uploadEmail(couponNotificationEntity.getId(), originalTargetFileName, targetFileInputStream);
        String targetFilePathAndName = FileNameUtils.updateOBSPrefixNotificationSavedFile(originalTargetFileName);

        String originalImageFileName = imageFile.getOriginalFilename();
        InputStream imageFileInputStream = imageFile.getInputStream();
        uploadImage(couponNotificationEntity.getId(), originalImageFileName, imageFileInputStream);
        String imageFilePathAndName = FileNameUtils.updateOBSPrefixNotificationSavedFile(originalImageFileName);

        couponNotificationEntity.setNotificationTargetFile(targetFilePathAndName);
        couponNotificationEntity.setNotificationImageFileName(imageFilePathAndName);
        couponNotificationRepository.save(couponNotificationEntity);

        return CouponNotificationDto.build(couponNotificationEntity, imageRootUrl);
    }

    @Override
    @CouponActionAuthorityCheck(ResourceAuthorityType.Constants.ADD)
    @Transactional
    public CouponNotificationDto updateCouponNotification(Long couponId,
                                                          Long couponNotificationId,
                                                          CouponNotificationInput couponNotificationInput,
                                                          MultipartFile targetFile,
                                                          MultipartFile imageFile) throws IOException {

        if (couponNotificationInput == null) {
            throw new InvalidInputCouponException("invalid notification input.");
        }

        Optional<CCoupon> optionalCCoupon = couponRepository.findById(couponId);
        CCoupon couponEntity = optionalCCoupon.orElseThrow(() -> new NotFoundCouponException("cannot find coupon: " + couponId));

        if (couponEntity.getIsDeleted() || couponEntity.getActivatedOn() != null) {
            throw new NotAllowedCouponException("cannot update coupon notification. " + couponNotificationId);
        }

        Optional<CCouponNotification> optionalCCouponNotification = couponNotificationRepository.findById(couponNotificationId);
        CCouponNotification couponNotificationEntity = optionalCCouponNotification.orElseThrow(() -> new NotFoundCouponNotificationException("cannot find coupon notification: " + couponNotificationId));

        couponMapper.updateCouponNotification(couponNotificationInput, couponNotificationEntity);

        LocalDateTime now = LocalDateTime.now();
        couponNotificationEntity.setModifiedOn(now);
        couponNotificationEntity.setModifiedBy(Utility.getUsername());
        couponNotificationRepository.save(couponNotificationEntity);

        String targetFilePathAndName = null;
        if (!targetFile.isEmpty()) {
            String originalTargetFileName = targetFile.getOriginalFilename();
            InputStream targetFileInputStream = targetFile.getInputStream();
            uploadEmail(couponNotificationEntity.getId(), originalTargetFileName, targetFileInputStream);
            targetFilePathAndName = FileNameUtils.updateOBSPrefixNotificationSavedFile(originalTargetFileName);
        }

        String imageFilePathAndName = null;
        if (!imageFile.isEmpty()) {
            String originalImageFileName = imageFile.getOriginalFilename();
            InputStream imageFileInputStream = imageFile.getInputStream();
            uploadImage(couponNotificationEntity.getId(), originalImageFileName, imageFileInputStream);
            imageFilePathAndName = FileNameUtils.updateOBSPrefixNotificationSavedFile(originalImageFileName);
        }

        if (targetFilePathAndName != null) {
            couponNotificationEntity.setNotificationTargetFile(targetFilePathAndName);
        }

        if (imageFilePathAndName != null) {
            couponNotificationEntity.setNotificationImageFileName(imageFilePathAndName);
        }

        couponNotificationRepository.save(couponNotificationEntity);

        return CouponNotificationDto.build(couponNotificationEntity, imageRootUrl);
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

        createCouponHistory(couponEntity);

        couponEntity.setIsNotified(false);
        couponEntity.setModifiedOn(LocalDateTime.now());
        couponEntity.setModifiedBy(Utility.getUsername());

        couponRepository.save(couponEntity);

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

        if ("S".equals(Utility.getUserInfo().getRoleid())) {
            return;
        }

        if (!securityGroupService.hasSecurityResourceActionAuthority(userId,"FG Coupon Management", action)) {
            throw new NotAuthorizedCouponException("not authorized to " + action);
        }
    }

    @Override
    public boolean checkCouponCodeUnique(String couponCode, Long couponCodeId) {

        if (couponCodeId == null) {
            List<CCouponCode> couponEntityList = couponCodeRepository.findByIsDeletedAndCouponCode(false, couponCode);
            if (!CollectionUtils.isEmpty(couponEntityList)) {
                return false;
            }
        } else {
            List<CCouponCode> couponEntityList = couponCodeRepository.findByIsDeletedAndCouponCodeAndIdNot(false, couponCode, couponCodeId);
            if (!CollectionUtils.isEmpty(couponEntityList)) {
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
        if (targetDate != null && LocalDateTime.now().isAfter(targetDate)) {
            throw new NotAllowedCouponException("cannot " + action + " coupon due to past due date. " + couponId);
        }
    }

    private void checkCouponPreviousActivationStatus(LocalDateTime activatedOn, Long couponId) {
        if (activatedOn != null) {
            throw new NotAllowedCouponException("cannot reactivate coupon. " + couponId);
        }
    }

    private void uploadEmail(Long couponNotificationId, String fileName, InputStream inputStream) {
        String containerName = properties.getRootNamePrefix() + properties.getRootEmailName();
        String objectPath = "" + couponNotificationId + "/" + fileName;

        fileUpload(containerName,objectPath,inputStream);
    }

    private void uploadImage(Long couponNotificationId, String fileName, InputStream inputStream) {
        String containerName = properties.getRootNamePrefix() + properties.getRootImageName();
        String objectPath = "" + couponNotificationId + "/" + fileName;

        fileUpload(containerName,objectPath,inputStream);
    }

    private void fileUpload(String containerName, String objectPath, InputStream inputStream) {
        CloseableHttpResponse response = factory.create().files()
                .upload(containerName, objectPath, inputStream, true)
                .executeWithoutHandler();

        HttpClientUtils.closeQuietly(
                response
        );
    }

    @CouponActionAuthorityCheck(ResourceAuthorityType.Constants.VIEW)
    @Transactional
    public PagedResult<CouponStatisticsDto> getCouponStatistics(int pn, int ps) {

        PagedResult<CouponStatisticsDto> result = new PagedResult<>();

        List<CCouponStatistics> couponStatisticsEntityList = couponStatisticsRepository.getCouponStatistics(PageRequest.of(pn - 1, ps));

        if (CollectionUtils.isEmpty(couponStatisticsEntityList)) {
            return result;
        }

        List<Long> couponIdList = couponStatisticsEntityList.stream().map(CCouponStatistics::getCouponId).collect(Collectors.toList());

        //List<CCoupon> couponEntityList = couponRepository.getCoupons(CouponRegisterType.FG.getValue(), PageRequest.of(pn - 1, ps));

        List<CCoupon> couponEntityList =  couponRepository.findByIdIn(couponIdList);
        
        List<CCouponCondition> couponConditionEntityList = couponConditionRepository.findByIsDeletedAndCouponIdIn(false, couponIdList);
        List<CCouponCode> couponCodeEntityList = couponCodeRepository.findByIsDeletedAndCouponIdIn(false, couponIdList);
        result.setRecords(CouponStatisticsDto.build(couponStatisticsEntityList, couponEntityList, couponConditionEntityList, couponCodeEntityList));

        long couponStatisticsCount = couponStatisticsRepository.getCouponStatCount();
        SingleValueResult total = new SingleValueResult();
        total.setTotalCount((int)couponStatisticsCount);

        result.setTotal(total);
        result.setPageNumber(pn);
        return result;
    }
    
}
