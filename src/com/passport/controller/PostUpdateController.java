package com.passport.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.passport.service.PostService;
import com.passport.vo.PostVO;

public class PostUpdateController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNum = req.getParameter("pageNum");
        int fileSize= 5 * 1024 * 1024;
        String uploadPath = req.getServletContext().getRealPath("/upload");
        
        try {
            MultipartRequest multi = new MultipartRequest(req, uploadPath, fileSize, "utf-8", new DefaultFileRenamePolicy());

            int num = Integer.parseInt(multi.getParameter("board_num"));
            String subject = multi.getParameter("board_subject");
            String content = multi.getParameter("board_content");
            String existFile = multi.getParameter("existing_file");

            PostVO post = new PostVO();
            post.setBoard_num(num);
            post.setBoard_subject(subject);
            post.setBoard_content(content);

            Enumeration<String> fileNames = multi.getFileNames();
            if(fileNames.hasMoreElements())
            {
                String fileName = fileNames.nextElement();
                String updateFile = multi.getFilesystemName(fileName);
                
                if(updateFile == null)
                	post.setBoard_file(existFile);
                else {
                	if(existFile != null){
                        String filePath = uploadPath + "/" + fileName;
                        File file = new File(filePath);
                        if(file.exists()) file.delete();
                	}
                	post.setBoard_file(updateFile);
                }
            }
            
            PostService service = PostService.getInstance();
            boolean result = service.postUpdate(post);

            if(result == true) {
    			req.setAttribute("info", "글을 수정했습니다!");
    			HttpUtil.forward(req, resp, "/postMyRead.do?num="+num);
    		} else if(result == false) {
    			req.setAttribute("error", "글수정 실패!");
    			HttpUtil.forward(req, resp, "/postMyList.do");
    		}
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("글 수정 오류 : " + e.getMessage());
        }

	}

}
