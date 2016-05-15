package servlets.hops;

import java.io.IOException;

import org.apache.http.client.HttpClient;

import redis.clients.jedis.Jedis;
import request.CalcHistogram;
import entity.CalcHistogramResult;
import entity.Histogram;
import entity.Histogram.Item;
import entity.Paper;
/**
 * Id1������������Id��Id2������������Id
 * 
 * ����2-hopҪ���·��ֻ�У�����Id1����>����Id3����>����Id2��һ������
 * Ҳ������һ��entity id,ʹ��
 * id = entity1.confer , author.id = id2.
 * 
 * 
 * 
 * @author user
 *
 */
public class TwoPATest implements Test {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7569215182506943791L;
	
	private static final String query = "And(Composite(C.CId=%d),Composite(AA.AuId=%d))";

	@Override
	public void test(Paper entity1, Paper entity2, HopResult result,
			HttpClient client, Jedis jedis) throws IOException {
		
		Long id1 =entity1.getId();
		Long id2 = entity2.getId();
		
		
		if(entity1.getConference()!=null){
			
			int total = -1;
			int offset = 0;
			while(offset<total||total<0){
				CalcHistogram calcHistogram = new CalcHistogram();
				calcHistogram.setExpr(String.format(query, entity1.getConference().getId(),entity2.getId()));
				calcHistogram.setAttributes("Id");
				calcHistogram.setCount(50);
				calcHistogram.setOffset(offset);
				
				String r = calcHistogram.doRequest(client,10000);
				CalcHistogramResult res = CalcHistogramResult.parse(r);
				total =res.getNum_entities();
				if(res.getNum_entities()>0){
					
					Histogram histogram = res.getHistograms().get(0);
					for(Item item:histogram.getItems()){
						result.push(id1,item.getValue(),id2);
						
					}
					
					
				}
				offset+=50;
			}
			
			
			
		}
		
		
		
		

	}

}
