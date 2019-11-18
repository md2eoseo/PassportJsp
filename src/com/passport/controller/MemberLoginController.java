package com.passport.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.passport.service.MemberService;

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

		MemberService service = MemberService.getInstance();
		status = service.memberLogin(id, password);
		
		HttpSession session = req.getSession();
		
		if(status == 1) {
			if(session.isNew() || session.getAttribute("userid") == null){
				session.setAttribute("userid", id);
				req.setAttribute("info", id + "님 환영합니다!");
			} else {
				req.setAttribute("info", session.getAttribute("userid") + "님이 현재 로그인 상태입니다!<br>사용자를 바꾸시려면 로그아웃을 해주세요!");
			}
		} else if(status == 0) {
			req.setAttribute("error", "비밀번호가 불일치합니다!");
		} else if(status == -1) {
			req.setAttribute("error", "존재하지 않는 아이디입니다!");
		}
		
		HttpUtil.forward(req, resp, "/index.jsp");
	};
}
