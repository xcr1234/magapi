package servlets.hops;

import java.io.IOException;

import org.apache.http.client.HttpClient;

import redis.clients.jedis.Jedis;
import request.CalcHistogram;
import entity.CalcHistogramResult;
import entity.Histogram;
import entity.Paper;
import entity.Histogram.Item;
/**
 * 作者id1-> 论文id3 ->论文id2
 * 找一个entity，使得 entity.auid = id1 , entity.confer = id2.
 * @author user
 *
 */
public class TwoAPTest implements Test {

	/**
	 * 
	 */
	private static final String query = "And(Composite(AA.AuId=%d),Composite(C.CId=%d))";

	
	private static final long serialVersionUID = 5742465467249747596L;

	@Override
	public void test(Paper entity1, Paper entity2, HopResult result,
			HttpClient client, Jedis jedis) throws IOException {
		

		Long id1 =entity1.getId();
		Long id2 = entity2.getId();
		if(entity2.getConference()!=null){
			int total = -1;
			int offset = 0;
			while(offset<total||total<0){
				CalcHistogram calcHistogram = new CalcHistogram();
				calcHistogram.setExpr(String.format(query, entity1.getId(),entity2.getConference().getId()));
				calcHistogram.setAttributes("Id");
				calcHistogram.setCount(50);
				
				calcHistogram.setOffset(offset);
				
				String r = calcHistogram.doRequest(client,5000);
				//System.out.println(r);
				CalcHistogramResult res = CalcHistogramResult.parse(r);
				total = res.getNum_entities();
				if(res.getNum_entities()>0){
					Histogram histogram = res.getHistograms().get(0);
					
					for(Item item:histogram.getItems()){
						result.push(id1,item.getValue(),id2);
						
					}
					
					
				}
				offset = offset + 50;
				
			}
			
			
			
		}
		
		
		
	}

}
