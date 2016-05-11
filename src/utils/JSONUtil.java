package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class JSONUtil {
	public static <T> T fromString(String json,Class<T> clazz){
		Gson gson = new Gson();
		return gson.fromJson(json, clazz);
		
	}
	
	public static String toJSON(Object object){
		Gson gson = new Gson();
		return gson.toJson(object);
	}
	
	

	
}
