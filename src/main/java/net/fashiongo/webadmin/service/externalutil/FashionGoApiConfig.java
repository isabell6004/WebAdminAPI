package net.fashiongo.webadmin.service.externalutil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by jinwoo on 2020-01-15.
 */
@Component
public class FashionGoApiConfig {

    public static String fashionGoApi;

    @Value("${webAdminapi.fashionGoApi.endpoint}")
    public void setFashionGoApi(String endpoint) {
        fashionGoApi = endpoint;
    }
}
