package com.springbook.view.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//HttpServlet상속- 서블릿 
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//컨트롤러 참조용 맵퍼
	private HandlerMapping handlerMapping;
	//jsp페이지 이동용 매퍼
	private ViewResolver viewResolver;

	//서블릿 초기화 시 ViewResolver객체 생성 및 초기화 
	public void init() throws ServletException {
		handlerMapping = new HandlerMapping();
		viewResolver = new ViewResolver();
		viewResolver.setPrefix("./");
		viewResolver.setSuffix(".jsp");
	}	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1. 클라이언트의 요청 path 정보를 추출한다.
		String uri = request.getRequestURI();//  "/biz/login.do", /getBoardList.do
		String path = uri.substring(uri.lastIndexOf("/"));// /login.do
		
		// 2. HandlerMapping을 통해 path에 해당하는 Controller를 검색한다.
		Controller ctrl = handlerMapping.getController(path); //getController("/login.do");
		
		// 3. 검색된 Controller를 실행한다.
		String viewName = ctrl.handleRequest(request, response);//  "getBoardList.do"
		
		// 4. ViewResolver를 통해 viewName에 해당하는 화면을 검색한다.
		String view = null;
		if (!viewName.contains(".do")) { 
			view = viewResolver.getView(viewName);//"getboardList" -> ./getboardList.jsp
		} else {
			view = viewName;//// "/getBoardList.do",
		}
		
		// 5. 검색된 화면으로 이동한다.
		response.sendRedirect(view); // "getBoardList.do", ./getboardList.jsp
		// 브라우저 url에 이동 경로 지정, http://localhost/biz/getBoardList.do, http://localthost/biz/getBoardList.jsp
	}

}
