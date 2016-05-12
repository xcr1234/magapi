package servlets.hops;

import java.io.IOException;

import org.apache.http.client.HttpClient;

import redis.clients.jedis.Jedis;
import entity.Paper;

public class EmptyTest implements Test {

	@Override
	public void test(Paper entity1, Paper entity2, HopResult result,HttpClient client,Jedis jedis)
			throws IOException {
		

	}

}
