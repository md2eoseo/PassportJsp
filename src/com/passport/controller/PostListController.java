package com.passport.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.passport.dao.DBConnection;
import com.passport.vo.PostVO;

public class PostListController implements Controller{

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
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
        ArrayList<PostVO> list =  dbcp.postList(listOpt);
        int listCount = dbcp.getPostListCount(listOpt);
        
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

        HttpUtil.forward(req, resp, "/postList.jsp");
    }
}
