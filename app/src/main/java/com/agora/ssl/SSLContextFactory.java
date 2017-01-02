package com.agora.ssl;

import android.content.res.AssetManager;
import android.util.Base64;

import com.agora.R;
import com.agora.app.AppContext;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

/**
 * Created by Ivan on 25/09/15.
 */
public class SSLContextFactory {

    private static String TRUST_STORE_FORMAT = AppContext.getContext().getString(R.string.trust_store_format);
    private static String TRUST_STORE_FILENAME = AppContext.getContext().getString(R.string.trust_store_name);
    private static String TRUST_STORE_PASSWORD = AppContext.getContext().getString(R.string.trust_store_pass);
    public static KeyStore trustStore;
    public static SSLContext sslContext;


    public static SSLContext makeContext(final KeyStore trustStore)  {
        if (sslContext==null) {
            try {
                sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, CustomTrustManager.getTrustManagerFactory(trustStore).getTrustManagers(),
                        null);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
        }
        return sslContext;
    }



    public static  KeyStore loadTrustStore(){
        if ( trustStore==null) {
            InputStream inputStream = null;

            // Create a new trustStore object
            try {
                trustStore = KeyStore.getInstance(TRUST_STORE_FORMAT);
            } catch (KeyStoreException e) {

            }
            // Open the trustStore file for reading
            AssetManager assetManager = AppContext.getContext().getAssets();
            try {
                inputStream = assetManager.open(TRUST_STORE_FILENAME,
                        AssetManager.ACCESS_UNKNOWN);
            } catch (IOException e) {

            }

            // Load the trustStore
            try {
                trustStore.load(inputStream,
                        TRUST_STORE_PASSWORD.toCharArray());
            } catch (NoSuchAlgorithmException e) {

            } catch (CertificateException e) {

            } catch (Exception e) {

            } finally {

                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        return trustStore;
    }
}
