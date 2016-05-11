package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.HttpClient;

import common.Conf;
import common.QueryString;
import entity.Author;
import entity.EvaluateResult;
import entity.Field;
import entity.Paper;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import request.Evaluate;
import utils.HttpUtils;

public class MagServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5870121637506507847L;

	/**
	 * Constructor of the object.
	 */
	public MagServlet() {
		super();
	}
	
	private HttpClient httpClient;

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); 
		
		jedisPool.close();
		
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		long a = System.currentTimeMillis();

		Long id1 = Long.valueOf(request.getParameter("id1"));
		Long id2 = Long.valueOf(request.getParameter("id2"));
		
		
		
		
		//对id1,id2进行预处理，判断它们的id类型，再开线程，处理它们的hop关系。
		Jedis jedis = jedisPool.getResource();
		
		String type1 = jedis.get(id1+"T");
		String type2 = jedis.get(id2+"T");
		if(type1==null||"nil".equals(type1)||type2==null||"nil".equals(type2)){
			Evaluate evaluate = new Evaluate();
			evaluate.setExpr(String.format(QueryString.eval_judge,id1,id2));
			evaluate.setCount(5000);
			evaluate.setOffset(0);
			evaluate.setAttributes("Id,F.FId,C.CId,J.JId,AA.AuId,AA.AfId");
			String res = evaluate.doRequest(httpClient);
			EvaluateResult result = EvaluateResult.parse(res);
			for(Paper entity:result.getEntities()){
				String id = entity.getId().toString();
				final String v1 = id+"T",v2=id+"R";
				
				if(entity.getAuthors()==null){
					jedis.set(v1, "author");
					
				}else{
					jedis.set(v1, "paper");
					if(entity.getFields()!=null){
						for(Field field:entity.getFields()){
							jedis.sadd(v2, field.getId().toString());
						}
					}
					
					if(entity.getConference()!=null){
						jedis.sadd(v2, entity.getConference().getId().toString());
					}
					if(entity.getJournal()!=null){
						jedis.sadd(v2, entity.getJournal().getId().toString());
					}
					if(entity.getAuthors()!=null){
						for(Author author:entity.getAuthors()){
							jedis.sadd(v2, author.getId().toString(),author.getAffiliationId().toString());
							
							
						}
					}
				}
				
			}
		}
		
		
	
		
		PrintWriter writer = response.getWriter();
		HopResult hopResult = new HopResult();
		if(OneHopTest.test(id1, id2, jedis)){
			
			hopResult.put(id1,id2);
		}
		
		
		
		writer.write(hopResult.toJSONArray());
		writer.close();
		
		long b = System.currentTimeMillis();
		
		System.out.println("Servlet mag time:"+(b-a)+"ms.");
		
		
		
		
	}
	
	
	private JedisPool jedisPool;
	
	public void init() throws ServletException {
		jedisPool = new JedisPool(Conf.redis_ip,Integer.valueOf(Conf.redis_port));
		httpClient = HttpUtils.client();
	}

}
