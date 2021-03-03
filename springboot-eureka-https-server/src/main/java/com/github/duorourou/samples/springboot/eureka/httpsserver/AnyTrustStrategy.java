package com.github.duorourou.samples.springboot.eureka.httpsserver;

import org.apache.http.ssl.TrustStrategy;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.security.cert.X509Certificate;

public class AnyTrustStrategy implements TrustStrategy, HostnameVerifier {
    @Override
    public boolean isTrusted(X509Certificate[] x509Certificates, String s) {
        System.out.println("any trust.........");
        return true;
    }

    @Override
    public boolean verify(String s, SSLSession sslSession) {
        return true;
    }
}
