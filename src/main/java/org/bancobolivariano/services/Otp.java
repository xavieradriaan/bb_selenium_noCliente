//src/main/java/org/bancobolivariano/services/Otp.java
package org.bancobolivariano.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Otp {

    public static String getOtp(String dni, String type) throws IOException {
        String url, soapAction, mensajeTemplate;
        if ("Entrust".equalsIgnoreCase(type)) {
            url = "https://soavides.bolivariano.fin.ec:5554/EntrustUtil/proxy/EntrustUtilProxyMS";
            soapAction = "http://service.wsentrust.ec.fin.bolivariano/EntrustServicePortType/getOTP";
            mensajeTemplate = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/src/test/resources/data/entrustTemplate.txt")));
        } else if ("Bytec".equalsIgnoreCase(type)) {
            url = "https://qckpayotpdesrv:3030/ServiceOTP.svc";
            soapAction = "http://otp.bolivariano.com.ec/manager/OTPManager/GenerarOTP";
            mensajeTemplate = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/src/test/resources/data/bytecTemplate.txt")));
        } else {
            throw new IllegalArgumentException("Invalid OTP type");
        }

        String mensaje = mensajeTemplate.replace("${cedula}", dni);
        OkHttpClient client = getHttpClient();
        MediaType mediaType = MediaType.parse("text/xml;charset=UTF-8");
        RequestBody body = RequestBody.create(mediaType, mensaje);
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("SOAPAction", soapAction)
                .addHeader("Content-Type", "text/xml;charset=UTF-8")
                .build();

        Response response = client.newCall(request).execute();
        return extractOtpValue(response.body().string(), type.equals("Entrust") ? "otp" : "*|OTP");
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
                    .connectTimeout(30, TimeUnit.SECONDS)  // Aumentar el tiempo de espera de conexión
                    .readTimeout(30, TimeUnit.SECONDS)     // Aumentar el tiempo de espera de lectura
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static String extractOtpValue(String xml, String selector) {
        String otpValue = null;
        try {
            Document doc = Jsoup.parse(xml);
            //System.out.println(doc);
            Element otpElement = doc.selectFirst(selector);
            if (otpElement != null) {
                otpValue = otpElement.text();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return otpValue;
    }
}