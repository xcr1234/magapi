package servlets.hops;

import java.io.IOException;

import org.apache.http.client.HttpClient;

import redis.clients.jedis.Jedis;
import request.CalcHistogram;
import entity.CalcHistogramResult;
import entity.Histogram;
import entity.Histogram.Item;
import entity.Paper;

public class TwoAATest implements Test {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3825700499227843332L;

	private static final String query1 = "And(Composite(AA.AuId=%d),Composite(AA.AuId=%d))";

	@Override
	public void test(Paper entity1, Paper entity2, HopResult result,
			HttpClient client, Jedis jedis) throws IOException {

		

		Long id1 = entity1.getId();
		Long id2 = entity2.getId();

		CalcHistogram calcHistogram = new CalcHistogram();
		calcHistogram.setExpr(String.format(query1, id1, id2));
		calcHistogram.setAttributes("Id,AA.AfId");
		calcHistogram.setCount(1000);
		calcHistogram.setOffset(0);
		String r = calcHistogram.doRequest(client, 15000);
		CalcHistogramResult res = CalcHistogramResult.parse(r);
		if (res.getNum_entities() > 0) {
			Histogram histogram = res.getHistograms().get(0);
			for (Item item : histogram.getItems()) {

				result.push(id1, item.getValue(), id2);

			}

			Histogram histogram2 = res.getHistograms().get(1);
			for (Item item : histogram2.getItems()) {

				result.push(id1, item.getValue(), id2);

			}

		}

	}

}
