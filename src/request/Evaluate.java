package request;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.HttpClient;
import org.apache.http.client.utils.URIBuilder;

import utils.HttpUtils;
import common.Conf;
import common.FieldNames;

public class Evaluate implements MagAPI{
	
	private String expr;
	
	private String model;
	
	private String attributes;
	
	private Integer count;
	
	private Integer offset;
	
	private String orderby;

	public String getExpr() {
		return expr;
	}

	public void setExpr(String expr) {
		this.expr = expr;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
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

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	@Override
	public String doRequest(HttpClient client) throws IOException {
		URI uri = null;
		
		try {
			URIBuilder builder = new URIBuilder(Conf.base_url+"evaluate");
			
			builder.setParameter(FieldNames.key, Conf.key);
			
			if(expr!=null) builder.setParameter("expr", expr);
			if(model!=null) builder.setParameter("model", model);
			if(attributes!=null) builder.setParameter("attributes", attributes);
			if(count!=null) builder.setParameter("count", count.toString());
			if(offset!=null) builder.setParameter("offset", offset.toString());
			if(orderby!=null) builder.setParameter("orderby", orderby);
			
			uri = builder.build();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		byte [] data = HttpUtils.doGet(uri, client, 5000);
		return new String(data);
		
		
	}
	
	
	

}
