package entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Conference implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -710871423321084453L;
	
	@SerializedName("CN")
	private String name;
	@SerializedName("CId")
	private Long id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Conference [name=" + name + ", id=" + id + "]";
	}
	
	

}
