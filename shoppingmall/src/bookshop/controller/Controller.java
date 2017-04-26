package bookshop.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.command.CommandAction;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = { "/Controller", "*.do" }, initParams = {
		@WebInitParam(name = "propertyConfig", value = "commandMapping.properties", description = "명령어처리") })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 명령어와 명령어처리클래스
	private Map<String, Object> commandMap = new HashMap<String, Object>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// initparams에서 config값 읽어옴
		String props = config.getInitParameter("propertyConfig");
		ServletContext context = config.getServletContext(); // 루트 경로
		String real_path = context.getRealPath("/property") + "\\" + props; // 시스템상
																			// 절대경로변환
		System.out.println("props=" + props);
		System.out.println("context=" + context);
		System.out.println("real_path=" + real_path);

		// 명령어 처리 클래스와 매핑정보 저장할 properties객체
		Properties pr = new Properties();
		FileInputStream f = null;
		try {
			f = new FileInputStream(real_path); // commandMapping파일 읽어옴
			pr.load(f); // 파일 내용을 pr객체에 저장
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (f != null)
				try {
					f.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		// 반복자 객체
		Iterator<?> key_iter = pr.keySet().iterator();
		while (key_iter.hasNext()) {
			String command = (String) key_iter.next(); // 명령어
			String class_name = pr.getProperty(command); // 명령어처리클래스
			try {
				Class<?> command_class = Class.forName(class_name);
				Object command_instance = command_class.newInstance();
				commandMap.put(command, command_instance); // map(명령어,클래스)추가

				System.out.println("command=" + command_class);
				System.out.println("command_instance=" + command_instance);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestPro(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		requestPro(request, response);
	}

	// 웹 브라우저 요청을 분석하고 모델실행및 처리결과를 뷰로 보내는 역할
	private void requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String view = null;
		CommandAction com = null;
		try {
			String command = request.getRequestURI();
			if(command.indexOf(request.getContextPath())==0){
				System.out.println("indexof가 0이여야지 실행="+request.getContextPath());
				command = command.substring(request.getContextPath().length()); //우리가했던 명령어길이만큼 짤라옴
			}
			//요청 명령어처리command서비스에서 명령어에따라 가져옴
			com = (CommandAction)commandMap.get(command);
			view = com.requestPro(request, response);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		//view 값 설정후 forward로 요청응답객체 같이 보내줌
		System.out.println("com="+com);
		System.out.println("view="+view);
		request.setAttribute("cont", view);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}
}
