package common;

public class QueryString {
	
	public static final String eval_judge = "Or(Id=%d,Id=%d)";
	
	public static final String calc_au_expr = "Composite(AA.AuId=%d)";
	
	public static final String calc_au_pa = "And(Composite(AA.AuId=%d),Id=%d)";

}
