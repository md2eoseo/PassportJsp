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
			req.setAttribute("error", "Please, fill out this form!");
			HttpUtil.forward(req, resp, "/memberInsert.jsp");
			return;
		}
		
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPassword(password);
		member.setName(name);
		member.setMail(mail);
		
		MemberService service = MemberService.getInstance();
		service.memberInsert(member);
		
		req.setAttribute("id", id);
		HttpUtil.forward(req, resp, "/result/memberInsertOutput.jsp");
	};
}
