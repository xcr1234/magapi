package entity;

import java.io.Serializable;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CalcHistogramResult  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7331015179604212804L;
	
	
	@Override
	public String toString() {
		return "CalcHistogramResult [expr=" + expr + ", num_entities="
				+ num_entities + ", histograms=" + histograms + ", aborted="
				+ aborted + "]";
	}

	private String expr;
	
	private Integer num_entities;
	
	private List<Histogram> histograms;
	
	private Boolean aborted;

	public String getExpr() {
		return expr;
	}

	public void setExpr(String expr) {
		this.expr = expr;
	}

	public Integer getNum_entities() {
		return num_entities;
	}

	public void setNum_entities(Integer num_entities) {
		this.num_entities = num_entities;
	}

	public List<Histogram> getHistograms() {
		return histograms;
	}

	public void setHistograms(List<Histogram> histograms) {
		this.histograms = histograms;
	}

	public Boolean getAborted() {
		return aborted;
	}

	public void setAborted(Boolean aborted) {
		this.aborted = aborted;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public static CalcHistogramResult parse(String str){
		Gson gson = new Gson();
		return gson.fromJson(str, CalcHistogramResult.class);
	}
	
	

}
