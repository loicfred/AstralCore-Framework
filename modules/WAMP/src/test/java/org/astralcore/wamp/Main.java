package org.astralcore.wamp;

import org.astralcore.wamp.spring.WAMPBuilder;
import org.astralcore.wamp.obj.Domain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;

import static org.astralcore.wamp.spring.WAMPBuilder.LOCALHOST;

@SpringBootApplication
public class Main {

    static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
        setupWAMP();
    }

    private static void setupWAMP() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");

        Domain H1 = new Domain(LOCALHOST, "mysite.mu").headers(headers);
        H1.addSubdomain("accounts").headers(headers);
        H1.addSubdomain("admin").headers(headers);

        Domain H2 = new Domain(LOCALHOST, "test2.mu");
        H2.addSubdomain("accounts");
        H2.addSubdomain("admin");

        Domain H3 = new Domain(LOCALHOST, "maudonate.mu", "http://localhost:8080");

        WAMPBuilder builder = new WAMPBuilder()
                .regenerateCerts()
                .registerDomain(H1)
                .registerDomain(H2)
                .registerDomain(H3);
        builder.build();
    }
}
