package net.fashiongo.webadmin.controller;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.common.crypto.model.FGPublicKey;
import net.fashiongo.common.crypto.service.CryptoService;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiHeader;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CryptoController {

    private final CryptoService service;

    public CryptoController(CryptoService service) {
        this.service = service;
    }

    @GetMapping("/crypto/key")
    public JsonResponse<FGPublicKey> getPublicKey(@RequestParam(value = "default", required = false) boolean justDefault) {
        try {
            FGPublicKey publicKey = service.getPublicKey(justDefault, FashionGoApiHeader.getDefaultHeader());
            return JsonResponse.success(publicKey);
        } catch (Exception e) {
            log.error("FashionGOApi Get PublicKey Error : {}", e.getMessage());
            return JsonResponse.fail("Failed. Get key.");
        }
    }
}
