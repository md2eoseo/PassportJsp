package com.passport.controller;

import java.io.IOException;
//import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.passport.service.PostService;
import com.passport.vo.PostVO;

public class PostCreateController implements Controller {

public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {

		String board_id = req.getParameter("board_id");
		String board_subject = req.getParameter("board_subject");
		String board_content = req.getParameter("board_content");
		String board_file = req.getParameter("board_file");
		
		int status = -1;
		
		PostVO post = new PostVO();
		post.setBoard_id(board_id);
		post.setBoard_subject(board_subject);
		post.setBoard_content(board_content);
		post.setBoard_file(board_file);
		
		PostService service = PostService.getInstance();
		status = service.postCreate(post);
		
		if(status == 1) {
			req.setAttribute("info", board_id + "님이 글을 작성했습니다!");
			HttpUtil.forward(req, resp, "/posts.jsp");
		} else if(status == -1) {
			req.setAttribute("error", "글쓰기 실패!");
		}
		
//		// 업로드 파일 사이즈
//	    int fileSize= 5 * 1024 * 1024;
//	    // 업로드될 폴더 경로
//	    String uploadPath = req.getServletContext().getRealPath("/UploadFolder");
//
//	    try {
//	        
//	        // 파일업로드 
//	        MultipartRequest multi = new MultipartRequest(req, uploadPath, fileSize, "UTF-8", new DefaultFileRenamePolicy());
//
//	        // 파일이름 가져오기
//	        String fileName = "";
//	        Enumeration<String> names = multi.getFileNames();
//	        if(names.hasMoreElements())
//	        {
//	            String name = names.nextElement();
//	            fileName = multi.getFilesystemName(name);
//	        }
//	        
//	        BoardDAO dao = BoardDAO.getInstance();
//	        PostVO borderData = new PostVO();
//	        
//	        borderData.setBoard_num(dbcp.getSeq()); // 시퀀스값 가져와 세팅
//	        borderData.setBoard_id(multi.getParameter("board_id")); // 히든값
//	        borderData.setBoard_subject(multi.getParameter("board_subject"));
//	        borderData.setBoard_content(multi.getParameter("board_content"));
//	        borderData.setBoard_file(multi.getParameter("board_file"));
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        System.out.println("글 작성 오류 : " + e.getMessage());
//	    }
//	    return forward;
	};
	
	


}
