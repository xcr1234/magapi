package common;

public final class Conf {
	
	private Conf(){throw new RuntimeException();}
	
	public final static String base_url = "https://oxfordhk.azure-api.net/academic/v1.0/";
	
	public final static String key = "f7cc29509a8443c5b3a5e56b0e38b5a6";
	
	public final static String redis_ip = "127.0.0.1";
	
	public final static String redis_port = "6379";
	
}
