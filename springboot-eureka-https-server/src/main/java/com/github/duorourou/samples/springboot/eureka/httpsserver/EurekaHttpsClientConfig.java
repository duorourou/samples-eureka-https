package com.github.duorourou.samples.springboot.eureka.httpsserver;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClientImpl;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@Configuration
public class EurekaHttpsClientConfig {

    @Value("${ssl.eureka-client.key-store}")
    String keyStoreFileName;

    @Value("${ssl.eureka-client.key-store-password}")
    String keyStorePassword;

    @Bean
    @Primary
    public DiscoveryClient.DiscoveryClientOptionalArgs discoveryClientOptionalArgs()
            throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        EurekaJerseyClientImpl.EurekaJerseyClientBuilder builder =
                new EurekaJerseyClientImpl.EurekaJerseyClientBuilder();
        builder.withClientName("eureka-https-client");
        SSLContext cont = new SSLContextBuilder()
                .setProtocol("TLS")
                .loadTrustMaterial(new AnyTrustStrategy())
                .build();
        builder.withCustomSSL(cont);
        builder.withMaxTotalConnections(10);
        builder.withMaxConnectionsPerHost(10);
        builder.withHostnameVerifier(new NoopHostnameVerifier());
        DiscoveryClient.DiscoveryClientOptionalArgs args = new DiscoveryClient.DiscoveryClientOptionalArgs();
        args.setEurekaJerseyClient(builder.build());
        args.setHostnameVerifier(new AnyTrustStrategy());
        args.setSSLContext(cont);
        return args;
    }

    @Bean
    @Primary
    public CloseableHttpClient closeableHttpClient(SSLConnectionSocketFactory sslConnectionSocketFactory) {
        return HttpClients.custom()
                .setSSLHostnameVerifier(new AnyTrustStrategy())
                .setSSLSocketFactory(sslConnectionSocketFactory)
                .build();
    }

    @Bean
    @Primary
    public SSLConnectionSocketFactory sslConnectionSocketFactory()
            throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        return new SSLConnectionSocketFactory(
                SSLContexts.custom().loadTrustMaterial(null,
                        new AnyTrustStrategy()).build(),
                NoopHostnameVerifier.INSTANCE);
    }

}
