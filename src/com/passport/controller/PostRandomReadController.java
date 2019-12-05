package com.passport.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.passport.service.PostService;
import com.passport.vo.PostVO;

public class PostRandomReadController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {        
        PostService service = PostService.getInstance();
        PostVO post = service.postRandomRead();
        
        req.setAttribute("post", post);
        
        HttpUtil.forward(req, resp, "/postRead.jsp");
	}

}
