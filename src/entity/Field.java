package entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Field implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7275743267725887539L;
	
	@JsonProperty("FId")
	private Long id;
	
	@JsonProperty("FN")
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
	@Override
	public String toString() {
		return "Field [id=" + id + ", name=" + name + "]";
	}
	

}
