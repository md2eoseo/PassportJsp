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
		int status = -1;

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
		status = service.memberLogin(member);
		
		if(status == 1) {
			req.setAttribute("id", id);
			req.setAttribute("info", "님 환영합니다!");
			HttpUtil.forward(req, resp, "/index.jsp");
		} else if(status == 0) {
			req.setAttribute("error", "비밀번호가 불일치합니다!");
			HttpUtil.forward(req, resp, "/index.jsp");
		} else if(status == -1) {
			req.setAttribute("error", "존재하지 않는 아이디입니다!");
			HttpUtil.forward(req, resp, "/index.jsp");
		}
	};
}
