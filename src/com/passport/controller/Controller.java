package com.passport.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public interface Controller {
	
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	
}
