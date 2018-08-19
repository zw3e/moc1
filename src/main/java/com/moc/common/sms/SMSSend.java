package com.moc.common.sms;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class SMSSend {

	
	 public static final String validateCodeSMS = "6ae1b2b1e4a544b3900cdd11b3a745e7";
	 
	 private static final String WSSE_HEADER_FORMAT = "UsernameToken Username=\"%s\",PasswordDigest=\"%s\",Nonce=\"%s\",Created=\"%s\"";

	 private static final String AUTH_HEADER_VALUE = "WSSE realm=\"SDP\",profile=\"UsernameToken\",type=\"Appkey\"";

	 
	 public static void main(String[] args) {
		 String templateParas = "[\"369751\"]";
		 
		 try {
			SendSMS("13770536960", validateCodeSMS, templateParas );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
	    
	    public static String SendSMS(String receiver,String templateId,String templateParas)throws Exception{

	        //请从应用管理页面获取APP接入地址，替换url中的ip地址和端口
	        //String url = "https://10.10.10.168:10443/sms/batchSendSms/v1";
	        String url = "https://117.78.29.66:10443/sms/batchSendSms/v1";
	    	
	        //请从应用管理页面获取APP_Key和APP_Secret进行替换
	        String appKey = "92D1bADMKX2V22traNw227lkn4BF";
	        String appSecret = "69olCe3ceVbWBbwS30iSY312R2HY";

	        //填写短信签名中的通道号，请从签名管理页面获取
	        String sender = "1069100121280234";
	        //填写短信接收人号码
	       // String receiver = "13770536960";
	        //状态报告接收地址，为空或者不填表示不接收状态报告
	        String statusCallBack = "";

	        //请从模板管理页面获取模板ID进行替换
	        //String templateId = "2e406afce7fb45468e95969dc69c9e0f";
	        //模板变量请务必根据实际情况修改，查看更多模板变量规则
	        //如模板内容为“您有${NUM_2}件快递请到${TXT_32}领取”时，templateParas可填写为[\"3\",\"人民公园正门\"]
	        //双变量示例：String templateParas = "[\"3\",\"人民公园正门\"]";
	       // String templateParas = "[\"369751\"]";
	        
	        String body = buildRequestBody(sender, receiver, templateId, templateParas, statusCallBack);
	        System.out.println("body is " + body);

	        String wsseHeader = buildWsseHeader(appKey, appSecret);
	        System.out.println("wsse header is " + wsseHeader);

	        //如果JDK版本低于1.8，可使用如下代码
	        //CloseableHttpClient client = HttpClients.custom()
	        //        .setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
	        //            @Override
	        //            public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
	        //                return true;
	        //            }
	        //        }).build()).setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();

	        //如果JDK版本是1.8，可使用如下代码
	        CloseableHttpClient client = HttpClients.custom()
	                    .setSSLContext(new SSLContextBuilder().loadTrustMaterial(null,
	                            (x509CertChain, authType) -> true).build())
	                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
	                    .build();

	        HttpResponse response = client.execute(RequestBuilder.create("POST")
	                    .setUri(url)
	                    .addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
	                    .addHeader(HttpHeaders.AUTHORIZATION, AUTH_HEADER_VALUE)
	                    .addHeader("X-WSSE", wsseHeader)
	                    .setEntity(new StringEntity(body)).build());

	        System.out.println(response.toString());
	        System.out.println(EntityUtils.toString(response.getEntity()));

	    	
	    	return "done";
	    }
	    
	    

	    
//	    public static void main(String[] args) throws Exception{
//
//	        //请从应用管理页面获取APP接入地址，替换url中的ip地址和端口
//	        //String url = "https://10.10.10.168:10443/sms/batchSendSms/v1";
//	        String url = "https://117.78.29.66:10443/sms/batchSendSms/v1";
//	    	
//	        //请从应用管理页面获取APP_Key和APP_Secret进行替换
//	        String appKey = "92D1bADMKX2V22traNw227lkn4BF";
//	        String appSecret = "69olCe3ceVbWBbwS30iSY312R2HY";
//
//	        //填写短信签名中的通道号，请从签名管理页面获取
//	        String sender = "1069100121280234";
//	        //填写短信接收人号码
//	        String receiver = "13770536960";
//	        //状态报告接收地址，为空或者不填表示不接收状态报告
//	        String statusCallBack = "";
//
//	        //请从模板管理页面获取模板ID进行替换
//	        String templateId = "2e406afce7fb45468e95969dc69c9e0f";
//	        //模板变量请务必根据实际情况修改，查看更多模板变量规则
//	        //如模板内容为“您有${NUM_2}件快递请到${TXT_32}领取”时，templateParas可填写为[\"3\",\"人民公园正门\"]
//	        //双变量示例：String templateParas = "[\"3\",\"人民公园正门\"]";
//	        String templateParas = "[\"369751\"]";
//	        
//	        String body = buildRequestBody(sender, receiver, templateId, templateParas, statusCallBack);
//	        System.out.println("body is " + body);
//
//	        String wsseHeader = buildWsseHeader(appKey, appSecret);
//	        System.out.println("wsse header is " + wsseHeader);
//
//	        //如果JDK版本低于1.8，可使用如下代码
//	        //CloseableHttpClient client = HttpClients.custom()
//	        //        .setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
//	        //            @Override
//	        //            public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
//	        //                return true;
//	        //            }
//	        //        }).build()).setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();
//
//	        //如果JDK版本是1.8，可使用如下代码
//	        CloseableHttpClient client = HttpClients.custom()
//	                    .setSSLContext(new SSLContextBuilder().loadTrustMaterial(null,
//	                            (x509CertChain, authType) -> true).build())
//	                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
//	                    .build();
//
//	        HttpResponse response = client.execute(RequestBuilder.create("POST")
//	                    .setUri(url)
//	                    .addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
//	                    .addHeader(HttpHeaders.AUTHORIZATION, AUTH_HEADER_VALUE)
//	                    .addHeader("X-WSSE", wsseHeader)
//	                    .setEntity(new StringEntity(body)).build());
//
//	        System.out.println("===="+response.toString());
//	        System.out.println(EntityUtils.toString(response.getEntity()));
//
//	    }

	    static String buildRequestBody(String sender, String receiver, String templateId, String templateParas,
	                                   String statusCallbackUrl) {

	        List<NameValuePair> keyValues = new ArrayList<>();

	        keyValues.add(new BasicNameValuePair("from", sender));
	        keyValues.add(new BasicNameValuePair("to", receiver));
	        keyValues.add(new BasicNameValuePair("templateId", templateId));
	        keyValues.add(new BasicNameValuePair("templateParas", templateParas));
	        keyValues.add(new BasicNameValuePair("statusCallback", statusCallbackUrl));

	        return URLEncodedUtils.format(keyValues, StandardCharsets.UTF_8);
	    }

	    static String buildWsseHeader(String appKey, String appSecret) {
	        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss'Z'");
	        String time = sdf.format(new Date());
	        String nonce = UUID.randomUUID().toString().replace("-", "");

	        byte[] passwordDigest = DigestUtils.sha256(nonce + time + appSecret);
	        String hexDigest = Hex.encodeHexString(passwordDigest);
	        String passwordDigestBase64Str = Base64.encodeBase64String(hexDigest.getBytes(Charset.forName("utf-8")));
	        return String.format(WSSE_HEADER_FORMAT, appKey, passwordDigestBase64Str, nonce, time);
	    }
}
