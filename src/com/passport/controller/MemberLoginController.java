package com.passport.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.passport.service.MemberService;
import com.passport.vo.MemberVO;

public class MemberLoginController implements Controller {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
		
		String id = req.getParameter("id");
		String password = req.getParameter("password");

		// error
		if(id.isEmpty() || password.isEmpty()) {
			req.setAttribute("error", "입력하지 않은 정보가 있습니다!");
			HttpUtil.forward(req, resp, "/index.jsp");
			return;
		}
		
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPassword(password);
		
		MemberService service = MemberService.getInstance();
//		service.memberInsert(member);
		
		req.setAttribute("id", id);
		req.setAttribute("info", "님 환영합니다!");
		HttpUtil.forward(req, resp, "/index.jsp");
	};
}
