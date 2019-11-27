package com.passport.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.passport.service.PostService;

public class PostDeleteController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String post = req.getParameter("post");
        int postNum = Integer.parseInt(post);
        String folder = req.getServletContext().getRealPath("upload");
        boolean status = false;
        
        PostService service = PostService.getInstance();
        status = service.postDelete(postNum, folder);
        
        if(status == true) {
			req.setAttribute("info", "글을 삭제했습니다!");
			HttpUtil.forward(req, resp, "/postMyList.do");
		} else if(status == false) {
			req.setAttribute("error", "글삭제 실패!");
			HttpUtil.forward(req, resp, "/postMyList.do");
		}
	}

}
