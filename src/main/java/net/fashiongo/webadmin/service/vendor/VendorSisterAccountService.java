package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.data.model.vendor.VendorSister;

import java.util.List;

/**
 * Created by jinwoo on 2020-01-09.
 */
public interface VendorSisterAccountService {

    List<VendorSister> getVendorSister(Integer wid);
    List<Integer> getVendorSisterChk(Integer wid, Integer sisterId);
    Boolean setVendorSister(Integer wid, Integer sisterId);
    Boolean delVendorSister(Integer mapID);
}
