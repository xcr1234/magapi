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
import utils.JSONUtil;

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
		
		Paper entity1 = null;
		Paper entity2 = null;
		
		//对数据进行预处理
		if(!inRedis(id1.toString())||!inRedis(id2.toString())){
			Evaluate evaluate = new Evaluate();
			evaluate.setExpr(String.format(QueryString.eval_judge,id1,id2));
			evaluate.setCount(5000);
			evaluate.setOffset(0);
			evaluate.setAttributes("Id,F.FId,C.CId,J.JId,AA.AuId,AA.AfId");
			
			String res = evaluate.doRequest(client);
			EvaluateResult result = EvaluateResult.parse(res);
			if(result.getEntities().get(0).getId().equals(id1)){
				//表示entity1在前面
				entity1 = result.getEntities().get(0);
				entity2 = result.getEntities().get(1);
			}else{
				//entity1在后面
				entity1 = result.getEntities().get(1);
				entity2 = result.getEntities().get(0);
				
			}
			
			
			
			
			
		}else{
			String json1 = jedis.get(id1+"E");
			String json2 = jedis.get(id2+"E");
			entity1 = JSONUtil.fromString(json1, Paper.class);
			entity2 = JSONUtil.fromString(json2, Paper.class);
			
			
		}
		writeEntity(entity1);
		writeEntity(entity2);
	
		
		
		
	
		
		PrintWriter writer = response.getWriter();
		HopResult hopResult = new HopResult();
		
		if(OneHopTest.test(entity1, entity2, jedis)){
			
			hopResult.push(id1,id2);
		}
		
		
		writer.write(hopResult.toJSONArray());
		writer.close();
		
		long b = System.currentTimeMillis();
		
		System.out.println("Servlet mag time:"+(b-a)+"ms.");
		
		
		
		
	}
	
	private boolean inRedis(String key){
		
		return jedis.get(key)==null||"nil".equals(jedis.get(key));
	}
	
	private void writeEntity(Paper entity){ 
		Long id = entity.getId();
		jedis.set(id+"E", JSONUtil.toJSON(entity));
		//将实体写入缓存。
		if(entity.getAuthors()==null){
			//这个实体是paper
			jedis.set(id+"T", "paper");
			for(Author author:entity.getAuthors()){
				//注意，这里有个author了，把这个author也放入缓存里面.
				writeEntity(Paper.valueOf(author));
				jedis.sadd(id+"R", author.getId().toString());
				if(author.getAffiliationId()!=null){
					jedis.sadd(id+"R", author.getAffiliationId().toString());
				}
				
				
			}
			
			
		}else{
			jedis.set(id+"T","author");
			
			
			
		}
		if(entity.getFields()!=null){
			for(Field field:entity.getFields()){
				jedis.sadd(id+"R", field.getId().toString());
				
			}
			
		}
		if(entity.getConference()!=null){
			jedis.sadd(id+"R", entity.getConference().getId().toString());
		}
		
		if(entity.getJournal()!=null){
			jedis.sadd(id+"R", entity.getJournal().getId().toString());
		}
		
		
		
	}
	
	
	
	
	private JedisPool jedisPool;
	
	private Jedis jedis;
	
	public void init() throws ServletException {
		jedisPool = new JedisPool(Conf.redis_ip,Integer.valueOf(Conf.redis_port));
		client = HttpUtils.client();
		jedis = jedisPool.getResource();
	}

}
