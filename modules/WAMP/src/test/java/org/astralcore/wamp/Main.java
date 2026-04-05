package org.astralcore.wamp;

import org.astralcore.wamp.spring.WAMPBuilder;
import org.astralcore.wamp.obj.Domain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;

@SpringBootApplication
public class Main {

    static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
        setupWAMP();
    }

    private static void setupWAMP() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");

        Domain H1 = new Domain(WAMPBuilder.LOCALHOST, "mysite.com").headers(headers);
        H1.addSubdomain("www").headers(headers);
        H1.addSubdomain("admin").headers(headers);
        H1.addSubdomain("accounts").headers(headers);

        Domain H2 = new Domain(WAMPBuilder.LOCALHOST, "test2.com", "C:/Website/test2/");
        H2.addSubdomain("www", "C:/Website/test2_www/");

        Domain H3 = new Domain(WAMPBuilder.LOCALHOST, "myothersite.com", "http://localhost:8080");

        WAMPBuilder builder = new WAMPBuilder()
                .registerDomains(H1, H2, H3)
                .regenerateCerts();
        builder.build();
    }
}
