package com.passport.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.passport.service.PostService;
import com.passport.vo.PostVO;

public class PostReadController implements Controller {

	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int board_num = Integer.parseInt(req.getParameter("num"));
        String pageNum = req.getParameter("pageNum");
        
        PostService service = PostService.getInstance();
        PostVO post = service.postRead(board_num);
        boolean result = service.updateCount(board_num);
        
        req.setAttribute("post", post);
        req.setAttribute("pageNum", pageNum);
        
        if(result)
        	HttpUtil.forward(req, resp, "/postRead.jsp");
	}
}
