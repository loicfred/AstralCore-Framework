package org.astralcore.wamp.spring;

import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.net.SSLHostConfig;
import org.apache.tomcat.util.net.SSLHostConfigCertificate;
import org.springframework.boot.tomcat.servlet.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WAMPConfig {

//    @Bean
//    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> servletContainer() {
//        return factory -> {
//            Connector http = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
//            http.setPort(80);
//            http.setScheme("http");
//            http.setSecure(false);
//            http.setRedirectPort(443);
//
//            // HTTPS (port 443)
//            Connector https = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
//            https.setPort(443);
//            https.setScheme("https");
//            https.setSecure(true);
//
//            factory.addAdditionalConnectors(http, https);
//        };
//    }
}