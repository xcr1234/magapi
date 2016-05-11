package common;

public final class FieldNames {
	
	private FieldNames(){throw new RuntimeException();}

	public static final String key = "subscription-key";
	/**
	 * 表示可能是author_id，也可能是paper_id的ID。
	 */
	public static final String general_id = "general_id";
	
	public static final String general = "general_entity";
	
	public static final String jedis_ip = "jedis_ip";
	
	public static final String jedis_port = "jedis_port";
	
	public static final String hop_id = "hop_id";
	public static final String entity_type = "entity_type";
	
	public static final String calc_total = "total_count";
	
	public static final String attribute = "attribute";
	
	
	
	
}
