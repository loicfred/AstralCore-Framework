package org.astralcore.wamp.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.StandardCopyOption;

import static org.astralcore.core.util.FileUtils.downloadFileFromURL;
import static org.astralcore.core.util.TerminalUtils.runCommand;
import static org.astralcore.core.util.TerminalUtils.runCommandNoPrint;

public class OpenSSL {
    private static final Logger log = LoggerFactory.getLogger(OpenSSL.class);

    public static boolean IsDownloaded() {
        try {
            runCommandNoPrint(OPENSSLPATH, "-help");
            return true;
        } catch (Exception e) {
            log.error("OpenSSL installation not found: {}", e.getMessage());
            return false;
        }
    }
    public static boolean Download(){
        try {
            File mkcert = new File(OPENSSLPATH);
            if (mkcert.exists()) return true;
            log.info("Downloading OpenSSL...");
            downloadFileFromURL("https://github.com/FiloSottile/mkcert/releases/download/v1.4.4/" + OPENSSLPATH.split("/")[3], OPENSSLPATH, StandardCopyOption.REPLACE_EXISTING);
            if (mkcert.exists()) {
                log.info("OpenSSL successfully downloaded.");
                return true;
            }
        } catch (Exception e) {
            log.error("Failed to download OpenSSL: {}", e.getMessage());
        }
        return false;
    }

    protected static void MakeCertsSSL() {
        try {
            runCommandNoPrint(
                    OPENSSLPATH, "pkcs12", "-export",
                    "-in", "./WAMP/certs/wampdomains.pem",
                    "-inkey", "./WAMP/certs/wampdomains-key.pem",
                    "-out", "WAMP/certs/wampdomains.p12",
                    "-name", "wildcard",
                    "-password", "pass:password"
            );
            log.info("Successfully mapped SSL certificate from PEM to P12.");
        } catch (Exception e) {
            log.error("Failed to map SSL certificate from PEM to P12: {}", e.getMessage());
        }
    }


    protected static final String OPENSSLPATH = getOpenSSLPath();
    private static String getOpenSSLPath() {
        return "./bin/openssl/bin/openssl.exe";
    }
}
