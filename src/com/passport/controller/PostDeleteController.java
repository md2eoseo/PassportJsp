package com.passport.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.passport.dao.DBConnection;
import com.passport.service.PostService;

public class PostDeleteController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		DBConnection dbcp = DBConnection.getInstance();
        String post = req.getParameter("post");
        int postNum = Integer.parseInt(post);
        String folder = req.getServletContext().getRealPath("upload");
        boolean status = false;
        
        try {
        	PostService service = PostService.getInstance();
            status = service.postDelete(postNum, folder);
            
            if(status == true) {
            	int user_postnum = dbcp.userInfo((String) req.getSession().getAttribute("userid"));
                req.getSession().setAttribute("user_postnum", user_postnum);
    			req.setAttribute("info", "글을 삭제했습니다!");
    			HttpUtil.forward(req, resp, "/postMyList.do");
    		} else if(status == false) {
    			req.setAttribute("error", "글삭제 실패!");
    			HttpUtil.forward(req, resp, "/postMyList.do");
    		}
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("글 삭제 오류 : " + e.getMessage());
            req.setAttribute("error", "글삭제 실패!");
			HttpUtil.forward(req, resp, "/postMyList.do");
        }

	}

}
