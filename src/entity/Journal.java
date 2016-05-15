package entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Journal  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3941352636612808991L;
	
	@JsonProperty("JId")
	private Long id;
	
	@JsonProperty("JN")
	private String name;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
