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
		list.put("/memberLogin.do", new MemberLoginController());
		list.put("/memberLogout.do", new MemberLogoutController());
		list.put("/postCreate.do", new PostCreateController());
		list.put("/postDelete.do", new PostDeleteController());
		list.put("/postUpdate.do", new PostUpdateController());
		list.put("/postUpdateForm.do", new PostUpdateFormController());
		list.put("/postList.do", new PostListController());
		list.put("/postRead.do", new PostReadController());
		list.put("/postMyList.do", new PostMyListController());
		list.put("/index.do", new IndexController());
		list.put("/FileDownload.do", new FileDownloadController());
		list.put("/postRandomRead.do", new PostRandomReadController());
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
