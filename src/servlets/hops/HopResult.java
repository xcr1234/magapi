package servlets.hops;

import utils.JSONUtil;





public class HopResult extends ConcurrentHashSet<Long[]>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5223538796959879198L;
	
	public boolean push(Long ... id){
		
		return super.add(id);
	}
	
	public String toJSONArray(){
		return JSONUtil.toJSON(this);
		
	}
	
	

}
