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
import servlets.hops.HopResult;
import utils.HttpUtils;
import utils.JSONUtil;

import common.Conf;

import entity.Author;
import entity.Field;
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
		
		
		
		PrintWriter writer = response.getWriter();
		HopResult hopResult = new HopResult();
		
		
		
		
		writer.write(hopResult.toJSONArray());
		writer.close();
		
		long b = System.currentTimeMillis();
		
		System.out.println("Servlet mag time:"+(b-a)+"ms.");
		
		
		
		
	}
	
	
	
	
	
	
	private JedisPool jedisPool;
	
	private Jedis jedis;
	
	public void init() throws ServletException {
		jedisPool = new JedisPool(Conf.redis_ip,Integer.valueOf(Conf.redis_port));
		client = HttpUtils.client();
		jedis = jedisPool.getResource();
	}

}
