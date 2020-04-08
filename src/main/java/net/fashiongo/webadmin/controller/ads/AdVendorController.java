package net.fashiongo.webadmin.controller.ads;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.model.ads.response.AdVendorResponse;
import net.fashiongo.webadmin.service.ads.AdVendorService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RestController
@RequestMapping("ad-vendors")
public class AdVendorController {

    private static final String SUCCESS_MESSAGE = null;

    private AdVendorService adVendorService;

    public AdVendorController(AdVendorService adVendorService) {
        this.adVendorService = adVendorService;
    }

    @GetMapping
    public JsonResponse<List<AdVendorResponse>> getVendorNames(@RequestParam(value = "shopActive", required = false) Boolean shopActive,
                                                               @RequestParam(value = "orderActive", required = false) Boolean orderActive,
                                                               @RequestParam(value = "ids", required = false) Integer[] ids) {


        List<Integer> vendorIds = Objects.nonNull(ids) ? Stream.of(ids).collect(Collectors.toList()) : null;

        List<AdVendorResponse> responses = adVendorService.getVendorNames(shopActive, orderActive, vendorIds);

        return new JsonResponse<>(Boolean.TRUE, SUCCESS_MESSAGE, responses);
    }
}
