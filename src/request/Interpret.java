package request;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.HttpClient;
import org.apache.http.client.utils.URIBuilder;

import utils.HttpUtils;
import common.Conf;
import common.FieldNames;

public class Interpret implements MagAPI{
	
	private String query;
	
	private String model;
	
	private Integer complete;
	
	private Integer count;
	
	private Integer offset;
	
	private Integer timeout;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getComplete() {
		return complete;
	}

	public void setComplete(Integer complete) {
		
		if(complete!=0&&complete!=1){
			throw new IllegalArgumentException("complete should be 0 or 1:"+complete);
		}
		
		this.complete = complete;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	
	public String doRequest(HttpClient client) throws IOException{
		
		URI uri = null;
		try {
			URIBuilder builder = new URIBuilder(Conf.base_url+"interpret");
			if(query!=null) builder.setParameter("query", query);
			if(model!=null) builder.setParameter("model", model);
			if(complete!=null) builder.setParameter("complete", complete.toString());
			if(count!=null) builder.setParameter("count", count.toString());
			if(offset!=null) builder.setParameter("offset", offset.toString());
			if(timeout!=null) builder.setParameter("timeout", timeout.toString());
			builder.setParameter(FieldNames.key, Conf.key);
			uri = builder.build();
		} catch (URISyntaxException e) {
			
			e.printStackTrace();
		}
		byte [] data = HttpUtils.doGet(uri, client, 5000);
		return new String(data);
		
		
	}
	

}
