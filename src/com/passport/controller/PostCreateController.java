package com.passport.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.passport.service.PostService;
import com.passport.vo.PostVO;

public class PostCreateController implements Controller {

public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
	
//		String board_id = req.getParameter("board_id");
//		String board_subject = req.getParameter("board_subject");
//		String board_content = req.getParameter("board_content");
//		String board_file = req.getParameter("board_file");
		int fileSize = 5 * 1024 * 1024;
		String uploadPath = req.getServletContext().getRealPath("/upload");
		boolean status = false;
		
		try {
			MultipartRequest multi = new MultipartRequest(req, uploadPath, fileSize, "utf-8", new DefaultFileRenamePolicy());
			String fileName = "";
			Enumeration<String> names = multi.getFileNames();
			if(names.hasMoreElements()) {
				String name = names.nextElement();
				fileName = multi.getFilesystemName(name);
			}
			
			PostVO post = new PostVO();
			post.setBoard_id(multi.getParameter("board_id"));
			post.setBoard_subject(multi.getParameter("board_subject"));
			post.setBoard_content(multi.getParameter("board_content"));
			post.setBoard_file(fileName);
			post.setBoard_markers(multi.getParameter("board_markers"));
			
			PostService service = PostService.getInstance();
			status = service.postCreate(post);
			if(status == true) {
				System.out.println("글을 작성했습니다");
				req.setAttribute("info", "글을 작성했습니다!");
				HttpUtil.forward(req, resp, "/postList.do");
			} else if(status == false) {
				req.setAttribute("error", "글쓰기 실패!");
				HttpUtil.forward(req, resp, "/postList.do");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
            System.out.println("글 작성 오류 : " + e.getMessage());
            req.setAttribute("error", "글쓰기 실패!");
			HttpUtil.forward(req, resp, "/postList.do");
		}
	};
	
	


}
