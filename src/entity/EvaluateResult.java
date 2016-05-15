package entity;

import java.io.Serializable;
import java.util.List;

import utils.JSONUtil;

public class EvaluateResult  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4085640673749223430L;
	
	private String expr;
	
	private List<Paper> entities;

	public String getExpr() {
		return expr;
	}

	public void setExpr(String expr) {
		this.expr = expr;
	}

	public List<Paper> getEntities() {
		return entities;
	}

	public void setEntities(List<Paper> entities) {
		this.entities = entities;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "EvaluateResult [expr=" + expr + ", entities=" + entities + "]";
	}
	
	public static EvaluateResult parse(String json){
		return JSONUtil.fromString(json, EvaluateResult.class);
	}

}
