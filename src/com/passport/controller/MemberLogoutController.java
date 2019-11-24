package com.passport.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class MemberLogoutController implements Controller {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
		HttpSession session = req.getSession(false);
		
		if(session != null && session.getAttribute("userid") != null) {
			session.invalidate();
			req.setAttribute("info", "로그아웃되었습니다!");
			HttpUtil.forward(req, resp, "/index.do");
		} else {
			req.setAttribute("error", "로그인한 사용자가 없습니다!");
			HttpUtil.forward(req, resp, "/index.do");
		}
	};
}
