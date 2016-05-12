package servlets.hops;


import java.util.HashSet;

import com.google.gson.Gson;

public class HopResult extends HashSet<Long[]>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5223538796959879198L;
	
	public synchronized boolean push(Long ... id){
		return super.add(id);
	}
	
	public String toJSONArray(){
		Gson gson = new Gson();
		return gson.toJson(this);
		
	}

}
