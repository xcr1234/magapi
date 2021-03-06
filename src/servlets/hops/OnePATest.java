package servlets.hops;

import java.io.IOException;
import java.util.Objects;

import org.apache.http.client.HttpClient;

import redis.clients.jedis.Jedis;
import entity.Author;
import entity.Paper;

public class OnePATest implements Test {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5666795704410257115L;

	@Override
	public void test(Paper entity1, Paper entity2, HopResult result,HttpClient client,Jedis jedis)
			throws IOException {
		Long id1 =entity1.getId();
		Long id2 = entity2.getId();
		if(entity1.getAuthors()!=null){
			for(Author author:entity1.getAuthors()){
				if(Objects.equals(author.getId(),id2)){
					
					result.push(id1,id2);
					return;
					
				}
			}
			
		}

	}

}
