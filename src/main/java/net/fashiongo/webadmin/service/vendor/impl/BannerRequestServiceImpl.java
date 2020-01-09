package net.fashiongo.webadmin.service.vendor.impl;

import net.fashiongo.webadmin.dao.primary.ListVendorImageTypeRepository;
import net.fashiongo.webadmin.dao.primary.VendorImageRequestRepository;
import net.fashiongo.webadmin.data.entity.primary.VendorImageRequestEntity;
import net.fashiongo.webadmin.data.model.vendor.BannerRequestCount;
import net.fashiongo.webadmin.data.model.vendor.BannerRequestResponse;
import net.fashiongo.webadmin.data.model.vendor.VendorImageRequestResponse;
import net.fashiongo.webadmin.data.repository.primary.vendor.VendorImageRequestApprovalType;
import net.fashiongo.webadmin.data.repository.primary.vendor.VendorImageRequestEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.vendor.VendorImageRequestOrderingType;
import net.fashiongo.webadmin.data.repository.primary.vendor.VendorImageRequestSelectParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetBannerRequestParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetDenyBannerParameter;
import net.fashiongo.webadmin.model.primary.ListVendorImageType;
import net.fashiongo.webadmin.model.primary.VendorImageRequest;
import net.fashiongo.webadmin.service.vendor.BannerRequestNewService;
import net.fashiongo.webadmin.service.vendor.BannerRequestService;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BannerRequestServiceImpl implements BannerRequestService {

    private final ListVendorImageTypeRepository listVendorImageTypeRepository;
    private final VendorImageRequestEntityRepository vendorImageRequestEntityRepository;
    private final VendorImageRequestRepository vendorImageRequestRepository;
    private final BannerRequestNewService bannerRequestNewService;

    public BannerRequestServiceImpl(ListVendorImageTypeRepository listVendorImageTypeRepository,
                                    VendorImageRequestEntityRepository vendorImageRequestEntityRepository,
                                    VendorImageRequestRepository vendorImageRequestRepository,
                                    BannerRequestNewService bannerRequestNewService) {
        this.listVendorImageTypeRepository = listVendorImageTypeRepository;
        this.vendorImageRequestEntityRepository = vendorImageRequestEntityRepository;
        this.vendorImageRequestRepository = vendorImageRequestRepository;
        this.bannerRequestNewService = bannerRequestNewService;
    }

    /**
     *
     * Description Example
     * @since 2018. 11. 13.
     * @author Reo
     */
    @Override
    @Transactional(value = "primaryTransactionManager", readOnly = true)
    public List<ListVendorImageType> getVendorImageType() {
        return listVendorImageTypeRepository.findAllByOrderByVendorImageTypeID();
    }

    @Override
    @Transactional
    public BannerRequestResponse getBannerRequest(GetBannerRequestParameter parameters) {
        VendorImageRequestSelectParameter parameter = VendorImageRequestSelectParameter.builder()
                .pageNumber(parameters.getPageNum())
                .pageSize(parameters.getPageSize())
                .wholesalerId(null)
                .wholesalerName(parameters.getSearchKeyword())
                .vendorImageTypeId(parameters.getSearchType())
                .approvalType(VendorImageRequestApprovalType.getType(parameters.getSearchStatus()))
                .active(null)
                .searchFrom(parameters.getFromDate() != null ? LocalDateTime.parse(parameters.getFromDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss")) : null)
                .searchTo(parameters.getToDate() != null ? LocalDateTime.parse(parameters.getToDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss")) : null)
                .orderingType(VendorImageRequestOrderingType.getType(parameters.getOrderby()))
                .showDeleted(null)
                .build();

        Page<VendorImageRequestEntity> result = vendorImageRequestEntityRepository.getVendorImageRequests(parameter);

        return BannerRequestResponse.builder()
                .bannerImageList(result.getContent().stream().map(VendorImageRequestResponse::convert).collect(Collectors.toList()))
                .total(Collections.singletonList(BannerRequestCount.builder().count(result.getTotalElements()).build()))
                .build();
    }

    /**
     *
     * Description Example
     * @since 2018. 11. 13.
     * @author Reo
     */
    @Override
    @Transactional(value = "primaryTransactionManager")
    public void setDenyBanner(SetDenyBannerParameter request) {
        VendorImageRequest vendorImageRequest = getVendorImageRequest(request.getImageRequestId());
        vendorImageRequest.denyBanner(request.getDenialReason(), Utility.getUsername());
        vendorImageRequestRepository.save(vendorImageRequest);

        // TODO: call vendor API - done
        bannerRequestNewService.rejectBanner(vendorImageRequest.getWholeSalerID(), request);
    }

    /**
     *
     * Description Example
     * @since 2018. 11. 13.
     * @author Reo
     */
    @Override
    @Transactional(value = "primaryTransactionManager")
    public void setApproveBanner(SetDenyBannerParameter request) {
        VendorImageRequest vendorImageRequest = getVendorImageRequest(request.getImageRequestId());
        vendorImageRequest.approveBanner(Utility.getUsername());
        vendorImageRequestRepository.save(vendorImageRequest);

        // TODO: call vendor API - done
        bannerRequestNewService.approveBanner(vendorImageRequest.getWholeSalerID(), request);
    }

    /**
     *
     * Description Example
     * @since 2018. 11. 13.
     * @author Reo
     */
    @Override
    @Deprecated
    @Transactional(value = "primaryTransactionManager")
    public void setRestoreBanner(SetDenyBannerParameter request) {
        VendorImageRequest vendorImageRequest = getVendorImageRequest(request.getImageRequestId());
        vendorImageRequest.restoreBanner();
        vendorImageRequestRepository.save(vendorImageRequest);
    }

    /**
     *
     * Description Example
     * @since 2018. 11. 13.
     * @author Reo
     */
    @Override
    @Transactional(value = "primaryTransactionManager")
    public void delBannerRequest(SetDenyBannerParameter request) {
        VendorImageRequest vendorImageRequest = getVendorImageRequest(request.getImageRequestId());
        vendorImageRequestRepository.delete(vendorImageRequest);

        // TODO: call vendor API - done
        bannerRequestNewService.deleteBanner(vendorImageRequest.getWholeSalerID(), request);
    }

    private VendorImageRequest getVendorImageRequest(Integer id) {
        return vendorImageRequestRepository.findByImageRequestID(id)
                .orElseThrow(() -> new IllegalArgumentException("The banner request was not found, id: " + id));
    }
}
