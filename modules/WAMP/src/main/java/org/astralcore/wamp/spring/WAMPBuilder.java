package org.astralcore.wamp.spring;

import org.astralcore.wamp.obj.Domain;

import java.util.ArrayList;
import java.util.List;

import static org.astralcore.wamp.spring.WAMPController.WAMPSERVER;

public class WAMPBuilder {
    public static final String LOCALHOST = "127.0.0.1";

    private final List<Domain> domains = new ArrayList<>();
    private boolean regenCerts = false;

    public WAMPBuilder() {}

    public WAMPBuilder regenerateCerts() {
        regenCerts = true;
        return this;
    }

    public WAMPBuilder registerDomain(Domain domain) {
        domains.add(domain);
        return this;
    }
    public WAMPBuilder registerDomains(Domain... domain) {
        domains.addAll(List.of(domain));
        return this;
    }

    public WAMP build() throws Exception {
        return WAMPSERVER = new WAMP(domains, regenCerts);
    }
}
