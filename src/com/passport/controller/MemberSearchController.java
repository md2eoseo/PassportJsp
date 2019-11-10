package com.passport.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.passport.service.MemberService;
import com.passport.vo.MemberVO;

public class MemberSearchController implements Controller {
	
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		String job = req.getParameter("job");
		String path = null;
		
		if(job.equals("search"))
			path = "/memberSearch.jsp";
		else if(job.equals("update"))
			path = "/memberUpdate.jsp";
		else if(job.equals("delete"))
			path = "/memberDelete.jsp";
		
		// error
		if(id.isEmpty()) {
			req.setAttribute("error", "Please, write down the ID!");
			HttpUtil.forward(req, resp, path);
			return;
		}
		
		MemberService service = MemberService.getInstance();
		MemberVO member = service.memberSearch(id);
		
		if (member == null) req.setAttribute("result", "no Member!!");
		req.setAttribute("member", member);
		if(job.equals("search")) path = "/result/memberSearchOutput.jsp";
		HttpUtil.forward(req, resp, path);
	}
}
