package entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Histogram implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 506828660434180138L;
	
	private String attribute;
	
	@JsonProperty("distinct_values")
	private Integer distinct;
	
	@JsonProperty("total_count")
	private Integer total;
	
	@JsonProperty("histogram")
	private List<Item> items;
	
	
	
	
	public List<Item> getItems() {
		return items;
	}


	public void setItems(List<Item> items) {
		this.items = items;
	}


	public String getAttribute() {
		return attribute;
	}


	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}


	public Integer getDistinct() {
		return distinct;
	}


	public void setDistinct(Integer distinct) {
		this.distinct = distinct;
	}


	public Integer getTotal() {
		return total;
	}


	public void setTotal(Integer total) {
		this.total = total;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "Histogram [attribute=" + attribute + ", distinct=" + distinct
				+ ", total=" + total + ", items=" + items + "]";
	}


	public static class Item implements Serializable{

		

		/**
		 * 
		 */
		private static final long serialVersionUID = 1844115811727030650L;
		
		private Long value;
		
		private Float logprob;
		
		private Integer count;

		public Long getValue() {
			return value;
		}

		public void setValue(Long value) {
			this.value = value;
		}

		public Float getLogprob() {
			return logprob;
		}

		public void setLogprob(Float logprob) {
			this.logprob = logprob;
		}

		public Integer getCount() {
			return count;
		}

		public void setCount(Integer count) {
			this.count = count;
		}

		@Override
		public String toString() {
			return "Item [value=" + value + ", logprob=" + logprob + ", count="
					+ count + "]";
		}

		
		
		
		
	}

}
