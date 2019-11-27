package com.passport.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.passport.service.PostService;
import com.passport.vo.PostVO;

public class PostUpdateFormController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageNum = req.getParameter("page");
        String num = req.getParameter("num");
        int boardNum = Integer.parseInt(num);
 
        PostService service = PostService.getInstance();
        PostVO post = service.getPost(boardNum);
        
        req.setAttribute("post", post);
        req.setAttribute("page", pageNum);
        
		HttpUtil.forward(req, resp, "/postUpdate.jsp");
	}

}
