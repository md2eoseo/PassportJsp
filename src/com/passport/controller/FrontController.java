package com.passport.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String charset = null;
	HashMap<String, Controller> list = null;
	
	@Override
	public void init(ServletConfig sc) throws ServletException {
		charset = sc.getInitParameter("charset");
		list = new HashMap<String, Controller>();
		list.put("/memberInsert.do", new MemberInsertController());
//		list.put("/memberSearch.do", new MemberSearchController());
//		list.put("/memberUpdate.do", new MemberUpdateController());
//		list.put("/memberDelete.do", new MemberDeleteController());
		list.put("/memberLogin.do", new MemberLoginController());
		list.put("/memberLogout.do", new MemberLogoutController());
//		list.put("/memberList.do", new MemberListController());
		list.put("/postCreate.do", new PostCreateController());
		list.put("/postList.do", new PostListController());
		list.put("/postRead.do", new PostReadController());
	}
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding(charset);
		String url = req.getRequestURI();  // /passport/memberInsert.do
		String contextPath = req.getContextPath();  // /passport
		String path = url.substring(contextPath.length());  // /memberInsert.do
		Controller subController = list.get(path);
		subController.execute(req, resp);
	}
}
