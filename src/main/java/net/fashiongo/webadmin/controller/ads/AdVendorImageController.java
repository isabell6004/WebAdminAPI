package net.fashiongo.webadmin.controller.ads;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.model.ads.request.VerifyVendorImageRequest;
import net.fashiongo.webadmin.model.ads.response.VerifyVendorImageResponse;
import net.fashiongo.webadmin.service.ads.AdVendorImageService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("vendor-images")
public class AdVendorImageController {

    private AdVendorImageService adVendorImageService;

    public AdVendorImageController(AdVendorImageService adVendorImageService) {
        this.adVendorImageService = adVendorImageService;
    }

    // spec : FG-AD-API/190
    @PostMapping("verify-approved")
    public JsonResponse<List<VerifyVendorImageResponse>> verifyVendorImages(@RequestBody @Valid VerifyVendorImageRequest request,
                                                                            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new JsonResponse<>(
                    Boolean.FALSE,
                    bindingResult.getAllErrors().stream()
                            .map(ObjectError::getDefaultMessage)
                            .collect(Collectors.joining(" ")),
                    null);
        }

        List<VerifyVendorImageResponse> responses = adVendorImageService.getVerifyVendorImages(
                request.getVendorIds(),
                request.getVendorImageTypes()
        );

        return new JsonResponse<>(true, null, responses);
    }
}
