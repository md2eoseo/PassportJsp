package com.passport.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.passport.service.MemberService;
import com.passport.vo.MemberVO;

public class MemberInsertController implements Controller {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
		
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String mail = req.getParameter("mail");

		// error
		if(id.isEmpty() || password.isEmpty() || name.isEmpty() || mail.isEmpty()) {
			req.setAttribute("error", "입력하지 않은 정보가 있습니다!");
			HttpUtil.forward(req, resp, "/index.jsp");
			return;
		}
		
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPassword(password);
		member.setName(name);
		member.setMail(mail);
		
		MemberService service = MemberService.getInstance();
		service.memberInsert(member);
		
		req.setAttribute("info", id + "님 회원가입이 완료되었습니다!");
		HttpUtil.forward(req, resp, "/index.jsp");
	};
}
