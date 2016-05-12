package servlets.hops;

import java.io.IOException;
import java.util.Objects;

import org.apache.http.client.HttpClient;

import redis.clients.jedis.Jedis;
import entity.Paper;

/**
 * one hop paper-paper test
 * @author user
 *
 */
public class OnePPTest implements Test{

	/**
	 * 
	 */
	private static final long serialVersionUID = -462905287669324296L;

	@Override
	public void test(Paper entity1, Paper entity2, HopResult result,HttpClient client,Jedis jedis)
			throws IOException {
		Long id1 =entity1.getId();
		Long id2 = entity2.getId();
		if(Objects.equals(id1,id2)){
			
			result.push(id1,id2);
			return;
			
		}
		if(entity1.getConference()!=null&&Objects.equals(entity1.getConference().getId(), id2)){
			result.push(id1,id2);
			return;
			
		}
	}
	
}
