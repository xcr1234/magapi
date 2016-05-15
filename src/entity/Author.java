package entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Author implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5812478355304382783L;
	
	@JsonProperty("AuId")
	private Long id;
	
	@JsonProperty("AuN")
	private String name;
	
	@JsonProperty("AfN")
	private String affiliationName;
	
	
	@JsonProperty("AfId")
	private Long affiliationId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAffiliationName() {
		return affiliationName;
	}
	public void setAffiliationName(String affiliationName) {
		this.affiliationName = affiliationName;
	}
	public Long getAffiliationId() {
		return affiliationId;
	}
	public void setAffiliationId(Long affiliationId) {
		this.affiliationId = affiliationId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", affiliationName="
				+ affiliationName + ", affiliationId=" + affiliationId + "]";
	}
	
	

}
