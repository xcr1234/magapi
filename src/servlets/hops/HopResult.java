package servlets.hops;



import com.google.gson.Gson;

public class HopResult extends ConcurrentHashSet<Long[]>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5223538796959879198L;
	
	public boolean push(Long ... id){
		for(Long i:id){
			if(i==null) return false;
		}
		return super.add(id);
	}
	
	public String toJSONArray(){
		Gson gson = new Gson();
		return gson.toJson(this);
		
	}
	
	

}
