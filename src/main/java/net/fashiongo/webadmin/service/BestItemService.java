package net.fashiongo.webadmin.service;

import net.fashiongo.webadmin.data.model.bestofbest.response.BestItemCategoryResponse;
import net.fashiongo.webadmin.data.model.bestofbest.response.BestItemProductListResponse;
import net.fashiongo.webadmin.data.model.bestofbest.response.VendorListResponse;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.service.externalutil.response.CollectionObject;
import net.fashiongo.webadmin.service.externalutil.response.SingleObject;

import java.util.List;

public interface BestItemService {
    CollectionObject<VendorListResponse> getVendorList(int pageNum,
                                                       int pageSize,
                                                       Long vendorId,
                                                       String vendorName,
                                                       Integer status,
                                                       Boolean listStatus);
    ResultCode addUnlistedVendor(Long vendorId);
    ResultCode removeUnlistedVendor(Long vendorId);
    SingleObject<Integer> getBestItemLimitPerVendor();
    ResultCode updateBestItemLimitPerVendor(int limitPerVendor);
    CollectionObject<BestItemProductListResponse> getListProductsList(Integer categoryId,
                                                                      Integer bodySizeId,
                                                                      Integer showItemOption,
                                                                      String vendorStatusList,
                                                                      Long vendorId,
                                                                      Integer productId,
                                                                      String productName,
                                                                      String itemName,
                                                                      String productDescription,
                                                                      String orderBy);
    ResultCode addUnlistedItem(String productIdList);
    CollectionObject<BestItemProductListResponse> getUnlistProductsList(Integer categoryId,
                                                                        Integer bodySizeId,
                                                                        Integer showItemOption,
                                                                        String vendorStatusList,
                                                                        Long vendorId,
                                                                        Integer productId,
                                                                        String productName,
                                                                        String itemName,
                                                                        String productDescription,
                                                                        String orderBy);
    ResultCode removeUnlistedItem(String productIdList);
    CollectionObject<BestItemCategoryResponse> getBestItemCategories();
}
