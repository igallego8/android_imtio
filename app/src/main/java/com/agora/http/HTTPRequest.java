package com.agora.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.agora.app.AppConfig;
import com.agora.ssl.NoSSLv3SocketFactory;
import com.agora.ssl.NullHostNameVerifier;
import com.agora.ssl.SSLContextFactory;

import java.io.InputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

/**
 * Created by Ivan on 7/10/15.
 */
public class HTTPRequest {

    public static SSLContext sslContext;
    public static KeyStore trustStore;
    public static SSLSocketFactory noSSLv3Factory;

    static {
        trustStore= SSLContextFactory.loadTrustStore();
        sslContext=SSLContextFactory.makeContext(trustStore);
        noSSLv3Factory = new NoSSLv3SocketFactory(sslContext.getSocketFactory());

    }
    public HTTPRequest(){
        CookieHandler.setDefault(new CookieManager());

    }



    public static Bitmap requestImageHttpGet(String urlString) {
        HttpURLConnection conn=null;
        InputStream instream=null;
        try {
            URL url = new URL(urlString);
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new NullHostNameVerifier());
            conn = (HttpURLConnection) url.openConnection();
            if(conn instanceof HttpsURLConnection) {
                ((HttpsURLConnection)conn).setSSLSocketFactory(noSSLv3Factory);
            }
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "image/jpg");
            conn.setConnectTimeout(AppConfig.TIMEOUT_HTTP_CONNECTION);
            instream = conn.getInputStream();
            Bitmap image = BitmapFactory.decodeStream(instream);
            conn.disconnect();
            instream.close();
            return image;
        }
        catch (Exception e)
        {
            // More about HTTP exception handling in another tutorial.
            // For now we just print the stack trace.
            e.printStackTrace();
        }finally{
            try{
                if(conn!=null) {
                    conn.disconnect();
                }

                if(instream!=null) {
                    instream.close();
                }
            } catch (Exception e) {
                e.printStackTrace(); //If you want further info on failure...
            }
        }
        return null;
    }



}
