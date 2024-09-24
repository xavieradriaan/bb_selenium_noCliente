package org.bancobolivariano.services;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Otp {
    public static String getOtpEntrust(String dni) throws IOException{
        String mensaje = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:ser=\"http://service.wsentrust.ec.fin.bolivariano/\">\r\n<soap:Header/>\r\n<soap:Body>\r\n<ser:getOTP>\r\n<!--Optional:-->\r\n<userId>${cedula}</userId>\r\n</ser:getOTP>\r\n</soap:Body>\r\n</soap:Envelope>\r\n";
        mensaje = mensaje.replace("${cedula}", dni);
        OkHttpClient client = getHttpClient();
        MediaType mediaType = MediaType.parse("text/xml;charset=UTF-8");
        RequestBody body = RequestBody.create(mediaType, mensaje);
        Request request = new Request.Builder()
                .url("https://soavides.bolivariano.fin.ec:5554/EntrustUtil/proxy/EntrustUtilProxyMS")
                .method("POST", body)
                .addHeader("action", "http://service.wsentrust.ec.fin.bolivariano/EntrustServicePortType/getOTP")
                .addHeader("Content-Type", "text/xml;charset=UTF-8")
                .build();


        Response response = client.newCall(request).execute();
        return extractOTPValue(response.body().string());
    }

    public static String getOtpBytec(String dni) throws IOException{
        String mensaje = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:ser=\"http://service.wsentrust.ec.fin.bolivariano/\">\r\n<soap:Header/>\r\n<soap:Body>\r\n<ser:getOTP>\r\n<!--Optional:-->\r\n<userId>${cedula}</userId>\r\n</ser:getOTP>\r\n</soap:Body>\r\n</soap:Envelope>\r\n";
        mensaje = mensaje.replace("${cedula}", dni);
        OkHttpClient client = getHttpClient();
        MediaType mediaType = MediaType.parse("text/xml;charset=UTF-8");
        RequestBody body = RequestBody.create(mediaType, mensaje);
        Request request = new Request.Builder()
                .url("https://soavides.bolivariano.fin.ec:5554/EntrustUtil/proxy/EntrustUtilProxyMS")
                .method("POST", body)
                .addHeader("action", "http://service.wsentrust.ec.fin.bolivariano/EntrustServicePortType/getOTP")
                .addHeader("Content-Type", "text/xml;charset=UTF-8")
                .build();


        Response response = client.newCall(request).execute();
        return extractOTPValue(response.body().string());
    }

    private static OkHttpClient getHttpClient() {
        try{
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }
            };
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            return new OkHttpClient().newBuilder()
                    .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0])
                    .hostnameVerifier((hostname, session) -> true)
                    .build();
        }catch (Exception ex){
        }
        return null;
    }

    public static String extractOTPValue(String xml) {
        String otpValue = null;
        try {
            Document doc = Jsoup.parse(xml);
            Element otpElement = doc.selectFirst("otp");
            if (otpElement != null) {
                otpValue = otpElement.text();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return otpValue;
    }
}
