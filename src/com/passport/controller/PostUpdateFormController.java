package com.passport.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.passport.service.PostService;
import com.passport.vo.PostVO;

public class PostUpdateFormController implements Controller {

	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type");
		String page = req.getParameter("page");
        int boardNum = Integer.parseInt(req.getParameter("num"));
 
        PostService service = PostService.getInstance();
        PostVO post = service.getPost(boardNum);
        
        req.setAttribute("boardNum", boardNum);
        req.setAttribute("post", post);
        req.setAttribute("page", page);
        req.setAttribute("type", type);
        
		HttpUtil.forward(req, resp, "/postUpdate.jsp");
	}

}
