package net.fashiongo.webadmin.config;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class TomcatConfig implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    private static final int MAX_SWALLOW_SIZE = 50 * 1024 * 1024; // 50MB

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        factory.addConnectorCustomizers(connector -> {
            if (connector.getProtocolHandler() instanceof AbstractHttp11Protocol) {
                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(MAX_SWALLOW_SIZE);
            }
        });
    }
}
// reference : https://anshulgnit.blogspot.com/2018/05/spring-boot-why-and-how-to-configure-maxswallowsize-property-for-embedded-tomcat.html
//             https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.0-Migration-Guide#embedded-containers-package-structure
