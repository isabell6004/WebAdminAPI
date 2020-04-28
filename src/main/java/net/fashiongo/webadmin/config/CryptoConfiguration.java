package net.fashiongo.webadmin.config;

import net.fashiongo.common.crypto.service.CryptoService;
import net.fashiongo.common.crypto.service.impl.CryptoServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = "net.fashiongo.common.crypto")
public class CryptoConfiguration {

    @Value("${webAdminapi.crypto.endpoint}")
    private String url;

    @Bean
    public CryptoService cryptoService() {
        return new CryptoServiceImpl(url);
    }
}

