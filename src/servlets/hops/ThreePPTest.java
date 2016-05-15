package servlets.hops;

import java.io.IOException;

import org.apache.http.client.HttpClient;

import redis.clients.jedis.Jedis;
import request.Evaluate;
import utils.JSONUtil;
import entity.Author;
import entity.EvaluateResult;
import entity.Paper;

public class ThreePPTest implements Test {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1487693973932931613L;

	@Override
	public void test(final Paper entity1, final Paper entity2, final HopResult result,
			final HttpClient client, final Jedis jedis) throws IOException {

		final Long id1 = entity1.getId();
		final Long id2 = entity2.getId();
		
	
		

		if (entity1.getReference() != null) {
			for (Long cid : entity1.getReference()) {

				// 判断该entity是否在缓存中
				String value = jedis.get(cid + "E");
				Paper entity = null;
				if (value == null || "nil".equals(value)) {
					Evaluate evaluate = new Evaluate();
					evaluate.setExpr("Id=" + cid);
					evaluate.setAttributes("Id,F.FId,C.CId,J.JId,AA.AuId,AA.AfId");
					evaluate.setCount(5000);
					evaluate.setOffset(0);

					String res = evaluate.doRequest(client);
					EvaluateResult er = EvaluateResult.parse(res);
					entity = er.getEntities().get(0);
					jedis.set(cid + "E", JSONUtil.toJSON(entity));

				} else {
					entity = JSONUtil.fromString(value, Paper.class);
				}
				HopResult tmp = new HopResult();

				new TwoPPTest().test(entity, entity2, tmp, client, jedis);
				
				for(Long[] hop:tmp){
					if(hop.length>=3){
						result.push(id1,hop[0],hop[1],hop[2]);
					}
					
				}
			}

		}
		
		
		if(entity1.getConference()!=null){
			Long cid = entity1.getConference().getId();
			Paper entity = new Paper();
			entity.setId(cid);
			
			HopResult tmp = new HopResult();
			new TwoPPTest().test(entity, entity2, tmp, client, jedis);
			for(Long[] hop:tmp){
				if(hop.length>=3){
					result.push(id1,hop[0],hop[1],hop[2]);
				}
				
			}
		}
		if(entity1.getJournal()!=null){
			Long jid = entity1.getJournal().getId();
			Paper entity = new Paper();
			entity.setId(jid);
			
			HopResult tmp = new HopResult();
			new TwoPPTest().test(entity, entity2, tmp, client, jedis);
			
			for(Long[] hop:tmp){
				if(hop.length>=3){
					result.push(id1,hop[0],hop[1],hop[2]);
				}
				
			}
		}
		if(entity1.getAuthors()!=null){
			for(Author au:entity1.getAuthors()){
				Long auid  = au.getId();
				Long afid = au.getAffiliationId();
				
				Paper entityu = new Paper();
				entityu.setId(auid);

				HopResult tmp = new HopResult();
				new TwoPPTest().test(entityu, entity2, tmp, client, jedis);
				
				for(Long[] hop:tmp){
					if(hop.length>=3){
						result.push(id1,hop[0],hop[1],hop[2]);
					}
					
				}
				HopResult tmp2 = new HopResult();
				Paper entityf = new Paper();
				entityf.setId(afid);
				new TwoPPTest().test(entityf, entity2, tmp2, client, jedis);
				for(Long[] hop:tmp2){
					if(hop.length>=3){
						result.push(id1,hop[0],hop[1],hop[2]);
					}
					
				}
			}
			
		}
		

	}

}
