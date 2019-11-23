package com.passport.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.passport.dao.DBConnection;
import com.passport.vo.PostVO;

public class PostMyListController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
        HttpSession session = req.getSession();
		
		if(session.isNew() || session.getAttribute("userid") == null){
			req.setAttribute("error", "로그인된 사용자가 없습니다!");
			// 일단은 다른 페이지에서 내글 눌러도 index로 가게 되어있음
			HttpUtil.forward(req, resp, "/index.jsp");
		} else {
			String userid = (String) session.getAttribute("userid");
			
			int spage = 1;
	        String page = req.getParameter("page");
	        
	        if(page != null) spage = Integer.parseInt(page);
	        
	        String opt = req.getParameter("opt");
	        String condition = req.getParameter("condition");
			
			HashMap<String, Object> listOpt = new HashMap<String, Object>();
	        listOpt.put("opt", opt);
	        listOpt.put("condition", condition);
	        listOpt.put("start", spage*10-9);
	        
	        DBConnection dbcp = DBConnection.getInstance();
	        ArrayList<PostVO> list =  dbcp.postMyList(listOpt, userid);
	        int listCount = dbcp.getPostMyListCount(listOpt, userid);
	        
	        // 한 페이지 5개 포스트
	        // [이전] 페이지 5개 [다음]
	        int maxPage = (int)(listCount/10.0 + 0.9);

	        int startPage = (int)(spage/5.0 + 0.8) * 5 - 4;

	        int endPage = startPage + 4;
	        if(endPage > maxPage)    endPage = maxPage;
	        
	        req.setAttribute("spage", spage);
	        req.setAttribute("maxPage", maxPage);
	        req.setAttribute("startPage", startPage);
	        req.setAttribute("endPage", endPage);

	        req.setAttribute("listCount", listCount);
	        req.setAttribute("list", list);

	        HttpUtil.forward(req, resp, "/mypost.jsp");
		}
	}
}
