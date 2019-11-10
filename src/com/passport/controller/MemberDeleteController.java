package com.passport.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.passport.service.MemberService;

public class MemberDeleteController implements Controller {
	
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		
		MemberService service = MemberService.getInstance();
		service.memberDelete(id);
		
		HttpUtil.forward(req, resp, "/result/memberDeleteOutput.jsp");
	}
}
