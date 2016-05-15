package servlets.hops;

import java.io.IOException;
import java.util.Objects;

import org.apache.http.client.HttpClient;

import common.QueryString;
import redis.clients.jedis.Jedis;
import request.CalcHistogram;
import entity.Author;
import entity.CalcHistogramResult;
import entity.Paper;

public class TwoPPTest implements Test {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2657979874454460087L;

	@Override
	public void test(Paper entity1, Paper entity2, HopResult result,
			HttpClient client, Jedis jedis) throws IOException {
		Long id1 =entity1.getId();
		Long id2 = entity2.getId();
		
		if(entity1.getConference()!=null&&entity2.getConference()!=null&&entity1.getConference().getId().equals(entity2.getConference().getId())){
			result.push(id1,entity1.getConference().getId(),id2);
			
		}
		if(entity1.getJournal()!=null&&entity2.getJournal()!=null&&Objects.equals(entity1.getJournal().getId(), entity2.getJournal().getId())){
			
			result.push(id1,entity1.getJournal().getId(),id2);
			
		}
		if(entity1.getAuthors()!=null&&entity2.getAuthors()!=null){
			for(Author au1:entity1.getAuthors()){
				for(Author au2:entity2.getAuthors()){
					if(Objects.equals(au1.getId(), au2.getId())){
						
						result.push(id1,au1.getId(),id2);
					}
					if(Objects.equals(au1.getAffiliationId(), au2.getAffiliationId())){
						
						result.push(id1,au1.getAffiliationId(),id2);
					}
					
					
				}
				
			}
		}
		
		//判断引用论文id，也就是找一篇论文，其Id=entity1.rid，而rid=entity2.id。
		if(entity1.getReference()!=null){
			for(Long rid:entity1.getReference()){
				CalcHistogram calcHistogram = new CalcHistogram();
				calcHistogram.setAttributes("Id");
				calcHistogram.setExpr(String.format(QueryString.calc_con, rid,id2));
				calcHistogram.setCount(1);
				calcHistogram.setOffset(0);
				String r = calcHistogram.doRequest(client);
				CalcHistogramResult res = CalcHistogramResult.parse(r);
				if(res.getNum_entities()>0){
					result.push(id1,rid,id2);
					
				}
			}
		}
	

	}

}
