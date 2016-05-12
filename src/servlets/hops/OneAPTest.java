package servlets.hops;

import java.io.IOException;

import org.apache.http.client.HttpClient;

import common.QueryString;
import redis.clients.jedis.Jedis;
import request.CalcHistogram;
import entity.CalcHistogramResult;
import entity.Paper;

public class OneAPTest implements Test {

	@Override
	public void test(Paper entity1, Paper entity2, HopResult result,HttpClient client,Jedis jedis)
			throws IOException {
		Long id1 =entity1.getId();
		Long id2 = entity2.getId();
		
		CalcHistogram calcHistogram = new CalcHistogram();
		calcHistogram.setExpr(String.format(QueryString.calc_au_pa, id1,id2));
		calcHistogram.setAttributes("AA.AfId,Id,AA.AuId");
		calcHistogram.setCount(1);
		calcHistogram.setOffset(0);
		
		String r = calcHistogram.doRequest(client);
		CalcHistogramResult cr = CalcHistogramResult.parse(r);
		if(cr.getNum_entities()>0){
			result.push(id1,id2);
		}
		

	}

}
