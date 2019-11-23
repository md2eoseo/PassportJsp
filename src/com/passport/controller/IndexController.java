package com.passport.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.passport.dao.DBConnection;
import com.passport.vo.PostVO;

public class IndexController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
        DBConnection dbcp = DBConnection.getInstance();
        ArrayList<PostVO> list =  dbcp.postIndex();
        req.setAttribute("list", list);

        HttpUtil.forward(req, resp, "/index.jsp");
	}

}
