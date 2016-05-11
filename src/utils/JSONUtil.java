package utils;

import com.google.gson.Gson;

public final class JSONUtil {
	public static <T> T toString(String json,Class<T> clazz){
		Gson gson = new Gson();
		return gson.fromJson(json, clazz);
		
	}
	
	public static String toJSON(Object object){
		Gson gson = new Gson();
		return gson.toJson(object);
	}
	
	

	
}
