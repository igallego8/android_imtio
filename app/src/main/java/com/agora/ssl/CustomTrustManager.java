package com.agora.ssl;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidator;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXParameters;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * Created by Ivan on 25/09/15.
 */
public class CustomTrustManager {

   static  TrustManagerFactory trustManagerFactory;

    private static CustomTrustManager instance;

    public static CustomTrustManager getInstance(){
        if (instance ==null){
            instance = new  CustomTrustManager();
        }
        return instance;
    }

    public static TrustManagerFactory getTrustManagerFactory (KeyStore trustStore) {
        if (trustManagerFactory==null) {
            try {
                trustManagerFactory = TrustManagerFactory
                        .getInstance(TrustManagerFactory.getDefaultAlgorithm());
                try {
                    trustManagerFactory.init(trustStore);
                } catch (KeyStoreException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return trustManagerFactory;
    }
}
