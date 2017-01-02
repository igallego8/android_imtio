package com.agora.http.json;


import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import com.agora.app.AppConfig;
import com.agora.ssl.NullHostNameVerifier;
import com.agora.ssl.SSLContextFactory;
import com.agora.ssl.NoSSLv3SocketFactory;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;


public class HTTPJSONRequest {


    public static final String TAG="HTTPJSONRequest";

    public static SSLContext sslContext;
    public static KeyStore trustStore;
    public static SSLSocketFactory noSSLv3Factory;

    static {
        trustStore=SSLContextFactory.loadTrustStore();
        sslContext=SSLContextFactory.makeContext(trustStore);
        noSSLv3Factory = new NoSSLv3SocketFactory(sslContext.getSocketFactory());

    }
    public HTTPJSONRequest(){

        CookieHandler.setDefault(new CookieManager());

    }



    public static JSONObject requestHttpGet(String urlString) {
        Log.i(TAG, urlString);
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
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setConnectTimeout(AppConfig.TIMEOUT_HTTP_CONNECTION);
          /*  if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }*/

            instream = conn.getInputStream();
            String resultString= convertStreamToString(instream);
            Log.i("RESULT______",resultString);
            conn.disconnect();
            instream.close();
            //resultString = resultString.substring(8,resultString.length()-2);
            JSONObject jsonResponse = new JSONObject(resultString);
            return jsonResponse;
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


    public static JSONArray requestHttpGetArray(String urlString) {
        Log.i(TAG,urlString);
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
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setConnectTimeout(AppConfig.TIMEOUT_HTTP_CONNECTION);
          /*  if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }*/

            instream = conn.getInputStream();
            String resultString= convertStreamToString(instream);
            Log.i("RESULT______",resultString);
            conn.disconnect();
            instream.close();
            //resultString = resultString.substring(8,resultString.length()-2);
            JSONArray jsonResponse = new JSONArray(resultString);
            return jsonResponse;
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

    
    public static void uploadImage(String urlString,  HashMap<String,String> params){
        DataOutputStream os=null;
        HttpURLConnection conn=null;
        BufferedWriter writer=null;
        String response="";
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";

        try {
            String contentDispositionKey = "Content-Disposition: form-data; name=\"key\"";
            String contentDispositionEntity = "Content-Disposition: form-data; name=\"entity\"";
            String contentDisposition1 = "Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + "image1.jpg" + "\"";

            //String contentType = "Content-Type: image/jpg";
            //String contentType = "Content-Type: application/octet-stream";
            String contentType =  "Content-Type: text/plain; charset=UTF-8";

            // This is the standard format for a multipart request
            StringBuffer requestBody = new StringBuffer();
            requestBody.append(twoHyphens);
            requestBody.append(boundary);
            requestBody.append(lineEnd);

            requestBody.append(contentDispositionKey);
            requestBody.append(lineEnd);
            requestBody.append(lineEnd);
            requestBody.append(params.get("key"));
            requestBody.append(lineEnd);

            requestBody.append(twoHyphens);
            requestBody.append(boundary);
            requestBody.append(lineEnd);

            requestBody.append(contentDispositionEntity);
            requestBody.append(lineEnd);
            requestBody.append(lineEnd);
            requestBody.append(params.get("entity"));
            requestBody.append(lineEnd);

            requestBody.append(twoHyphens);
            requestBody.append(boundary);
            requestBody.append(lineEnd);

            requestBody.append(contentType);
            requestBody.append(lineEnd);
            requestBody.append(contentDisposition1);
            requestBody.append(lineEnd);
            requestBody.append(lineEnd);

            StringBuffer requestBody2 = new StringBuffer();
            requestBody2.append(lineEnd);
            requestBody2.append(twoHyphens);
            requestBody2.append(boundary);
            requestBody2.append(twoHyphens);
            requestBody2.append(lineEnd);


            URL url = new URL(urlString);
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new NullHostNameVerifier());
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("ENCTYPE", "multipart/form-data");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            conn.setConnectTimeout(AppConfig.TIMEOUT_HTTP_CONNECTION);
            conn.connect();
            os = new DataOutputStream(conn.getOutputStream());
            os.writeBytes(requestBody.toString());
            os.write(params.get("image").getBytes());
            os.writeBytes(requestBody2.toString());
            os.flush();

            Log.i("Length ",params.get("image").length()+"");
            int responseCode=conn.getResponseCode();
            Log.i("Response Code",responseCode+"");
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
            }
            Log.i("Response uploadImage:",response.length()+"");

        } catch (IOException e) {
            Log.e("ImageUploader", "Error uploading image", e);
        }finally{
            try{
                if(conn!=null) {
                    conn.disconnect();
                }

                if(os!=null) {
                    os.close();
                }

                if(writer!=null ) {
                    writer.close();
                }
            } catch (Exception e) {
                e.printStackTrace(); //If you want further info on failure...
            }
        }

    }



    public static JSONObject requestHttpPost(String urlString, String json) {
        Log.i(TAG,json);
        HttpURLConnection conn=null;
        OutputStream os=null;
        BufferedWriter writer = null;
        String response = "";
        try {
            URL url = new URL(urlString);
           // HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
           // HttpsURLConnection.setDefaultHostnameVerifier(new NullHostNameVerifier());
            conn = (HttpURLConnection) url.openConnection();
            //if(conn instanceof HttpsURLConnection) {
              //  ((HttpsURLConnection)conn).setSSLSocketFactory(noSSLv3Factory);
            //}
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            os = conn.getOutputStream();
            Log.i("Request", json);
            os.write(json.getBytes("UTF-8"));
            //printout.writeBytes(json.toString());
            //printout.flush();
            os.flush();
            int responseCode=conn.getResponseCode();
            Log.i("requestHttpPost", "ResponseCode:"+responseCode);
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
            }
            Log.i("Response",response);
            //resultString = resultString.substring(8,resultString.length()-2);
            JSONObject jsonResponse = new JSONObject(response);
            return jsonResponse;
        }
        catch (Exception e)
        {
           Log.e("requestHttpPost", e.getMessage());
        }finally{
            try{
                if(conn!=null) {
                    conn.disconnect();
                }

                if(os!=null) {
                    os.close();
                }

                if(writer!=null) {
                    writer.close();
                }
            } catch (Exception e) {
                Log.e("requestHttpPost", e.getMessage());
            }
        }
        return null;
    }



    public static JSONObject requestHttpPut(String urlString, String json) {
        Log.i(TAG,json);
        HttpURLConnection conn=null;
        OutputStream os=null;
        BufferedWriter writer = null;
        String response = "";


        try {
            URL url = new URL(urlString);
            // HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            // HttpsURLConnection.setDefaultHostnameVerifier(new NullHostNameVerifier());
            conn = (HttpURLConnection) url.openConnection();
            //if(conn instanceof HttpsURLConnection) {
            //  ((HttpsURLConnection)conn).setSSLSocketFactory(noSSLv3Factory);
            //}
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            os = conn.getOutputStream();
            Log.i("Request", json);
            os.write(json.getBytes("UTF-8"));
            //printout.writeBytes(json.toString());
            //printout.flush();
            os.flush();
            int responseCode=conn.getResponseCode();
            Log.i("requestHttpPost", "ResponseCode:"+responseCode);
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
            }
            Log.i("Response",response);
            //resultString = resultString.substring(8,resultString.length()-2);
            JSONObject jsonResponse = new JSONObject(response);
            return jsonResponse;
        }
        catch (Exception e)
        {
            Log.e("requestHttpPost", e.getMessage());
        }finally{
            try{
                if(conn!=null) {
                    conn.disconnect();
                }

                if(os!=null) {
                    os.close();
                }

                if(writer!=null) {
                    writer.close();
                }
            } catch (Exception e) {
                Log.e("requestHttpPost", e.getMessage());
            }
        }
        return null;
    }

    private static String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }


    private static String convertStreamToString(InputStream is) {
		/*
		 * To convert the InputStream to String we use the BufferedReader.readLine()
		 * method. We iterate until the BufferedReader return null which means
		 * there's no more data to read. Each line will appended to a StringBuilder
		 * and returned as String.
		 *
		 * (c) public domain: http://senior.ceng.metu.edu.tr/2009/praeda/2009/01/11/a-simple-restful-client-at-android/
		 */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
