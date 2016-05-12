package servlets.hops;

import java.io.IOException;

import org.apache.http.client.HttpClient;

import redis.clients.jedis.Jedis;
import request.CalcHistogram;
import entity.Paper;

public class TwoAATest implements Test {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3825700499227843332L;
	
	private static final String query = "And(Composite(AA.AuId=%d),Composite(AA.AfId=%d))";

	@Override
	public void test(Paper entity1, Paper entity2, HopResult result,
			HttpClient client, Jedis jedis) throws IOException {
		
		
		
	}

}
