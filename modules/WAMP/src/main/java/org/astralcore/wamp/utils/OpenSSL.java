package org.astralcore.wamp.utils;

import static org.astralcore.core.util.TerminalUtils.runCommandNoPrint;

public class OpenSSL {

    protected static boolean MakeCertsSSL() {
        try {
            runCommandNoPrint(
                    OPENSSLPATH, "pkcs12", "-export",
                    "-in", "./WAMP/certs/wampdomains.pem",
                    "-inkey", "./WAMP/certs/wampdomains-key.pem",
                    "-out", "WAMP/certs/wampdomains.p12",
                    "-name", "wildcard",
                    "-password", "pass:password"
            );
            return true;
        } catch (Exception e) {
            System.err.println("Failed to trust domain: " + e.getMessage());
            return false;
        }
    }


    protected static final String OPENSSLPATH = getOpenSSLPath();
    private static String getOpenSSLPath() {
        return "./bin/openssl/bin/openssl.exe";
    }
}
