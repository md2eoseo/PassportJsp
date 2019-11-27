package com.passport.controller;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownloadController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String fileName = req.getParameter("file_name");
        String folder = req.getServletContext().getRealPath("upload");
        String filePath = folder + "/" + fileName;
 
        try {
            File file = new File(filePath);
            byte b[] = new byte[(int) file.length()];
            
            resp.reset();
            resp.setContentType("application/octet-stream");
            
            String encoding = new String(fileName.getBytes("euc-kr"),"8859_1");
            
            resp.setHeader("Content-Disposition", "attachment;filename="+ encoding);
            resp.setHeader("Content-Length", String.valueOf(file.length()));
            
            if (file.isFile())
            {
                FileInputStream fileInputStream = new FileInputStream(file);
                ServletOutputStream servletOutputStream = resp.getOutputStream();
                
                int readNum = 0;
                while ( (readNum = fileInputStream.read(b)) != -1) {
                    servletOutputStream.write(b, 0, readNum);
                }
                servletOutputStream.close();
                fileInputStream.close();
            }
        } catch (Exception e) {
            System.out.println("Download Exception : " + e.getMessage());
        }
	}
}
