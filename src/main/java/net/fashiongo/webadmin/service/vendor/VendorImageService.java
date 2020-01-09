package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.data.model.vendor.DelVendorImageParameter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorImageParameter;
import net.fashiongo.webadmin.data.model.vendor.VendorImage;

import java.util.List;

/**
 * Created by jinwoo on 2020-01-08.
 */
public interface VendorImageService {
    Integer create(SetVendorImageParameter param);

    Integer delete(DelVendorImageParameter param);

    List<VendorImage> getVendorImage(Integer wid);
}
