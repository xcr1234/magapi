package utils;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public final class JSONUtil {

	static ObjectMapper mapper = null;
	

	public static <T> T fromString(String json, Class<T> clazz) throws JsonException{
		if(mapper == null){
			mapper = new ObjectMapper();
		}
		try {
			return mapper.readValue(json, clazz);
		} catch (IOException e) {
			throw new JsonException(e);
		}

	}

	public static String toJSON(Object object) throws JsonException{
		if(mapper == null){
			mapper = new ObjectMapper();
		}
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new JsonException(e);
		}

	}
	
	public static class JsonException extends RuntimeException{

		
		private static final long serialVersionUID = -8295094746976555877L;

		public JsonException() {
			super();
			
		}

		public JsonException(String message, Throwable cause,
				boolean enableSuppression, boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
			
		}

		public JsonException(String message, Throwable cause) {
			super(message, cause);
			
		}

		public JsonException(String message) {
			super(message);
			
		}

		public JsonException(Throwable cause) {
			super(cause);
		}
		
	}

}
