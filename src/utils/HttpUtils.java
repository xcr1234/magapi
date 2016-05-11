package utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;


public class HttpUtils {
	
	
	public static HttpClient client(){
		return HttpClients.custom().build();
	}
	
	public static HttpClient client(CookieStore cookiestore){
		return HttpClients.custom().setDefaultCookieStore(cookiestore).build();
	}
	
	public final static byte[] doPost(URI uri,List<? extends NameValuePair> params,HttpClient httpClient,int timeout) throws IOException {
		RequestConfig config = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();
		
		HttpPost httpPost = new HttpPost(uri);
		httpPost.setConfig(config);
		httpPost.setHeader("Connection","keep-alive");
		httpPost.setHeader("Cache-Control","max-age=0");
		
		
		httpPost.setEntity(new UrlEncodedFormEntity(params,"utf-8"));
		
		
		HttpResponse res = httpClient.execute(httpPost);
		httpPost.releaseConnection();
		return getByte(res);
		
	}
	
	
	
	public final static byte[] doGet(URI uri,HttpClient httpClient,int timeout)  throws IOException{
		
		RequestConfig config = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();
		
		HttpGet httpGet = new HttpGet(uri);
		httpGet.setConfig(config);
		httpGet.setHeader("Connection","keep-alive");
        httpGet.setHeader("Cache-Control","max-age=0");
        
        HttpResponse res =  httpClient.execute(httpGet);
        httpGet.releaseConnection();
        return getByte(res);
		
	}
	
	
	private static byte[] getByte(HttpResponse res) throws IOException{
		InputStream inputStream = res.getEntity().getContent();
		
		byte[] data = IOUtils.toByteArray(inputStream);
		
		return data;
		
	}
	
	
}
