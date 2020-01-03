package net.fashiongo.webadmin.service.vendor.impl;

import net.fashiongo.webadmin.dao.primary.ListVendorImageTypeRepository;
import net.fashiongo.webadmin.dao.primary.VendorImageRequestRepository;
import net.fashiongo.webadmin.model.pojo.parameter.SetDenyBannerParameter;
import net.fashiongo.webadmin.model.primary.ListVendorImageType;
import net.fashiongo.webadmin.model.primary.VendorImageRequest;
import net.fashiongo.webadmin.service.vendor.BannerRequestNewService;
import net.fashiongo.webadmin.service.vendor.BannerRequestService;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BannerRequestServiceImpl implements BannerRequestService {

    private final ListVendorImageTypeRepository listVendorImageTypeRepository;
    private final VendorImageRequestRepository vendorImageRequestRepository;
    private final BannerRequestNewService bannerRequestNewService;

    public BannerRequestServiceImpl(ListVendorImageTypeRepository listVendorImageTypeRepository,
                                    VendorImageRequestRepository vendorImageRequestRepository,
                                    BannerRequestNewService bannerRequestNewService) {
        this.listVendorImageTypeRepository = listVendorImageTypeRepository;
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
        request.setVendorId(vendorImageRequest.getWholeSalerID());
        bannerRequestNewService.rejectBanner(request);
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
        request.setVendorId(vendorImageRequest.getWholeSalerID());
        bannerRequestNewService.approveBanner(request);
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
        request.setVendorId(vendorImageRequest.getWholeSalerID());
        bannerRequestNewService.deleteBanner(request);
    }

    private VendorImageRequest getVendorImageRequest(Integer id) {
        return vendorImageRequestRepository.findByImageRequestID(id)
                .orElseThrow(() -> new IllegalArgumentException("The banner request was not found, id: " + id));
    }
}
