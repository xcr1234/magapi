package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.HttpClient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import request.Evaluate;
import servlets.hops.HopResult;
import utils.HttpUtils;
import utils.JSONUtil;

import common.Conf;
import common.QueryString;

import entity.EvaluateResult;
import entity.Paper;

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
	
	private HttpClient client;

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); 
		
		jedisPool.close();
		jedis.close();
		
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
		
		
		//预处理过程，将id1,id2转换为entity1和entity2
		Paper entity1 = null,entity2 = null;
		if(!inCache(id1+"E")||!inCache(id2+"E")){
			//进行查询
			Evaluate evaluate = new Evaluate();
			evaluate.setExpr(String.format(QueryString.eval_judge,id1,id2));
			evaluate.setCount(5000);
			evaluate.setOffset(0);
			evaluate.setAttributes("Id,F.FId,C.CId,J.JId,AA.AuId,AA.AfId,RId");
			String res = evaluate.doRequest(client);
			EvaluateResult result = EvaluateResult.parse(res);
			if(result.getEntities().get(0).getId().equals(id1)){
				entity1 = result.getEntities().get(0);
				entity2 = result.getEntities().get(1);
			}else{
				entity1 = result.getEntities().get(1);
				entity2 = result.getEntities().get(0);
			}
			
			
			jedis.set(id1+"E", JSONUtil.toJSON(entity1));
			jedis.set(id2+"E", JSONUtil.toJSON(entity2));
			
		}else{
			entity1 = JSONUtil.fromString(jedis.get(id1+"E"), Paper.class);
			entity2 = JSONUtil.fromString(jedis.get(id2+"E"), Paper.class);
			
			
		}
		
		
	
		
		
		PrintWriter writer = response.getWriter();
		HopResult hopResult = new HopResult();
		
		
		
		
		writer.write(hopResult.toJSONArray());
		writer.close();
		
		long b = System.currentTimeMillis();
		
		System.out.println("Servlet mag time:"+(b-a)+"ms.");
		
		
		
		
	}
	
	private boolean inCache(String id){
		String get = jedis.get(id);
		return get==null||"nil".equals(get);
	}
	
	
	
	
	
	
	private JedisPool jedisPool;
	
	private Jedis jedis;
	
	public void init() throws ServletException {
		jedisPool = new JedisPool(Conf.redis_ip,Integer.valueOf(Conf.redis_port));
		client = HttpUtils.client();
		jedis = jedisPool.getResource();
	}

}
