package net.fashiongo.webadmin.controller;

import net.fashiongo.webadmin.data.model.bestofbest.response.BestItemCategoryResponse;
import net.fashiongo.webadmin.data.model.bestofbest.response.BestItemProductListResponse;
import net.fashiongo.webadmin.data.model.bestofbest.response.VendorListResponse;
import net.fashiongo.webadmin.data.model.vendor.response.OrderActiveVendorResponse;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.service.BestItemService;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.externalutil.response.CollectionObject;
import net.fashiongo.webadmin.service.externalutil.response.SingleObject;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/bestitem", produces = "application/json")
public class  BestItemController {

    @Autowired
    private BestItemService bestItemService;

    @Autowired
    private CacheService cacheService;

    @GetMapping("/getOrderActiveVendors")
    public JsonResponse<List<OrderActiveVendorResponse>> getVendorList() {
        List<OrderActiveVendorResponse> data = bestItemService.getOrderActiveVendors();
        return new JsonResponse<>(true, null, data);
    }

    @GetMapping("/getVendorList")
    public JsonResponse<CollectionObject<VendorListResponse>> getVendorList(@RequestParam(value = "pn") int pageNum,
                                                                            @RequestParam(value = "ps") int pageSize,
                                                                            @RequestParam(value = "vendorId",required = false) Long vendorId,
                                                                            @RequestParam(value = "vendorName",required = false) String vendorName,
                                                                            @RequestParam(value = "status",required = false) Integer status,
                                                                            @RequestParam(value = "listStatus",required = true) Boolean listStatus) {
        CollectionObject<VendorListResponse> data = bestItemService.getVendorList(pageNum,pageSize,vendorId,vendorName,status,listStatus);
        return new JsonResponse<>(true, null, data);
    }


    @PostMapping(value = "/addUnlistedVendor/{vendorId}")
    public JsonResponse<ResultCode> addUnlistedVendor(@PathVariable long vendorId) throws IOException {
        ResultCode result = bestItemService.addUnlistedVendor(vendorId);
        return new JsonResponse<>(result.getSuccess(), result.getResultMsg(), null);
    }

    @DeleteMapping(value = "/removeUnlistedVendor/{vendorId}")
    public JsonResponse<ResultCode> removeUnlistedVendor(@PathVariable long vendorId) throws IOException {
        ResultCode result = bestItemService.removeUnlistedVendor(vendorId);
        return new JsonResponse<>(result.getSuccess(), result.getResultMsg(), null);
    }

    @GetMapping("/getBestItemLimitPerVendor")
    public JsonResponse<SingleObject<Integer>> getBestItemLimitPerVendor() {
        SingleObject<Integer> data = bestItemService.getBestItemLimitPerVendor();
        return new JsonResponse<>(true, null, data);
    }

    @PutMapping(value = "updateBestItemLimitPerVendor")
    public JsonResponse<ResultCode> updateBestItemLimitPerVendor(
            @RequestParam(value = "limitPerVendor") int limitPerVendor) throws IOException {
        ResultCode result = bestItemService.updateBestItemLimitPerVendor(limitPerVendor);

        return new JsonResponse<>(result.getSuccess(), result.getResultMsg(), null);

    }

    @GetMapping("/getListProductsList")
    public JsonResponse<CollectionObject<BestItemProductListResponse>> getListProductsList(@RequestParam(name = "categoryId", required = false) Integer categoryId,
                                                                                           @RequestParam(name = "bodySizeId", required = false) Integer bodySizeId,
                                                                                           @RequestParam(name = "showItemOption", required = false) Integer showItemOption,
                                                                                           @RequestParam(name = "vendorStatusList", required = false) List<String> vendorStatusList,
                                                                                           @RequestParam(name = "vendorId", required = false) Long vendorId,
                                                                                           @RequestParam(name = "productId", required = false) Integer productId,
                                                                                           @RequestParam(name = "productName", required = false) String productName,
                                                                                           @RequestParam(name = "itemName", required = false) String itemName,
                                                                                           @RequestParam(name = "productDescription", required = false) String productDescription,
                                                                                           @RequestParam(name = "orderBy") String orderBy) {

        String vendorStatusLists = null;

        if (vendorStatusList != null) {
            vendorStatusLists = String.join(",", vendorStatusList);
        }

        CollectionObject<BestItemProductListResponse> data = bestItemService.getListProductsList(categoryId,bodySizeId,showItemOption,vendorStatusLists,vendorId,productId,productName,itemName,productDescription,orderBy);
        return new JsonResponse<>(true, null, data);
    }
    @PostMapping(value = "/product")
    public JsonResponse<ResultCode> addUnlistedItem(@RequestParam(name = "productIdList") List<String> productIdList) {

        String productIdLists = String.join(",", productIdList);

        ResultCode result = bestItemService.addUnlistedItem(productIdLists);

        return new JsonResponse<>(result.getSuccess(), result.getResultMsg(), null);
    }

    @GetMapping("/getUnlistProductsList")
    public JsonResponse<CollectionObject<BestItemProductListResponse>> getUnlistProductsList(@RequestParam(name = "categoryId", required = false) Integer categoryId,
                                                                                             @RequestParam(name = "bodySizeId", required = false) Integer bodySizeId,
                                                                                             @RequestParam(name = "showItemOption", required = false) Integer showItemOption,
                                                                                             @RequestParam(name = "vendorStatusList", required = false) List<String> vendorStatusList,
                                                                                             @RequestParam(name = "vendorId", required = false) Long vendorId,
                                                                                             @RequestParam(name = "productId", required = false) Integer productId,
                                                                                             @RequestParam(name = "productName", required = false) String productName,
                                                                                             @RequestParam(name = "itemName", required = false) String itemName,
                                                                                             @RequestParam(name = "productDescription", required = false) String productDescription,
                                                                                             @RequestParam(name = "orderBy") String orderBy) {

        String vendorStatusLists = null;

        if (vendorStatusList != null) {
            vendorStatusLists = String.join(",", vendorStatusList);
        }

        CollectionObject<BestItemProductListResponse> data = bestItemService.getUnlistProductsList(categoryId,bodySizeId,showItemOption,vendorStatusLists,vendorId,productId,productName,itemName,productDescription,orderBy);

        return new JsonResponse<>(true, null, data);
    }

    @DeleteMapping(value = "/product")
    public JsonResponse<ResultCode> removeUnlistedItem(@RequestParam(name = "productIdList") List<String> productIdList) {

        String productIdLists = String.join(",", productIdList);

        ResultCode result = bestItemService.removeUnlistedItem(productIdLists);

        return new JsonResponse<>(result.getSuccess(), result.getResultMsg(), null);
    }

    @GetMapping("/getCategories")
    public JsonResponse<CollectionObject<BestItemCategoryResponse>> getBestItemCategories() {
        CollectionObject<BestItemCategoryResponse> data = bestItemService.getBestItemCategories();
        return new JsonResponse<>(true, null, data);
    }

    @PostMapping("/updateCache")
    public JsonResponse<Void> setUpdateCache() {

        cacheService.GetRedisCacheEvict("BestItems", null);

        return new JsonResponse<>(true, null, null);
    }

}
