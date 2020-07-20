package net.fashiongo.webadmin.controller;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.common.JsonResponse;
import net.fashiongo.webadmin.data.model.collection.CollectionBulkPatchParameter;
import net.fashiongo.webadmin.data.model.collection.CollectionSaveParameter;
import net.fashiongo.webadmin.data.model.collection.response.*;
import net.fashiongo.webadmin.model.product.command.category.ProductCategoryResponse;
import net.fashiongo.webadmin.service.CollectionService;
import net.fashiongo.webadmin.service.externalutil.response.CollectionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/collection", produces = "application/json")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @PostMapping
    public JsonResponse<Integer> saveCollection(@RequestPart("collection") CollectionSaveParameter collectionSaveParameter,
                                                @RequestPart(value = "desktopImageBannerFile", required = false) MultipartFile desktopImageBannerFile,
                                                @RequestPart(value = "mobileImageBannerFile", required = false) MultipartFile mobileImageBannerFile) throws IOException {
        Integer collectionId = collectionService.saveCollection(collectionSaveParameter, desktopImageBannerFile, mobileImageBannerFile);
        return new JsonResponse<>(true, null, collectionId);
    }

    @GetMapping("/getcollection")
    public JsonResponse<CollectionResponse> getCollection(@RequestParam(value = "collectionId") int collectionId) {
        CollectionResponse data = collectionService.getCollection(collectionId);
        return new JsonResponse<>(true, null, data);
    }

    @PatchMapping("/setcollection/{collectionId}")
    public JsonResponse<CollectionPatchResponse> setCollection(@PathVariable int collectionId,
                                            @RequestPart("collection") CollectionSaveParameter collectionSaveParameter,
                                            @RequestPart(value = "desktopImageBannerFile", required = false) MultipartFile desktopImageBannerFile,
                                            @RequestPart(value = "mobileImageBannerFile", required = false) MultipartFile mobileImageBannerFile) throws IOException {
        CollectionPatchResponse data = collectionService.setCollection(collectionId, collectionSaveParameter, desktopImageBannerFile, mobileImageBannerFile);
        return new JsonResponse<>(true, null, data);
    }

    @GetMapping("/getcollections")
    public JsonResponse<CollectionObject<CollectionListResponse>> getCollections(@RequestParam(value = "pn") int pageNum,
                                                                                 @RequestParam(value = "ps") int pageSize,
                                                                                 @RequestParam(value = "orderby") String orderBy,
                                                                                 @RequestParam(value = "title", required = false) String title,
                                                                                 @RequestParam(value = "type", required = false) Integer type,
                                                                                 @RequestParam(value = "status", required = false) Integer status,
                                                                                 @RequestParam(value = "fromDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
                                                                                 @RequestParam(value = "toDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate) {
        CollectionObject<CollectionListResponse> data = collectionService.getCollections(pageNum, pageSize, orderBy, title, type, status, fromDate, toDate);
        return new JsonResponse<>(true, null, data);
    }

    @PatchMapping("/setstatus")
    public JsonResponse<Void> setStatus(@RequestBody CollectionBulkPatchParameter collectionBulkPatchParameter) {
        collectionService.setStatus(collectionBulkPatchParameter);
        return new JsonResponse<>(true, null, null);
    }

    @GetMapping("/searchvendors")
    public JsonResponse<CollectionObject<CollectionVendorResponse>> searchVendors(@RequestParam("q") List<String> query,
                                                                                  @RequestParam(name = "order", required = false) String order) {
        CollectionObject<CollectionVendorResponse> data = collectionService.searchVendors(query, order);
        return new JsonResponse<>(true, null, data);
    }

    @GetMapping("/vendorpromotions")
    public JsonResponse<CollectionObject<CollectionVendorLatestPromotionResponse>> getVendorLatestPromotion(@RequestParam("ids") List<Integer> vendorIds) {
        CollectionObject<CollectionVendorLatestPromotionResponse> data = collectionService.getVendorLatestPromotion(vendorIds);
        return new JsonResponse<>(true, null, data);
    }

    @GetMapping("/searchproducts")
    public JsonResponse<CollectionObject<CollectionProductResponse>> searchProducts(@RequestParam("query") String query,
                                                                                    @RequestParam("start") int start,
                                                                                    @RequestParam("size") int size) {
        CollectionObject<CollectionProductResponse> data = collectionService.searchProducts(query, start, size);
        return new JsonResponse<>(true, null, data);
    }

    @GetMapping("/getproducts")
    public JsonResponse<CollectionObject<CollectionProductResponse>> getProducts(@RequestParam("collectionId") int collectionId) {
        CollectionObject<CollectionProductResponse> data = collectionService.getProducts(collectionId);
        return new JsonResponse<>(true, null, data);
    }

    @GetMapping("/getproductquickshopinfo")
    public JsonResponse<CollectionProductQuickShopResponse> getProductQuickShopInfo(@RequestParam("productId") int productId) {
        CollectionProductQuickShopResponse data = collectionService.getProductQuickShopInfo(productId);
        return new JsonResponse<>(true, null, data);
    }
}
