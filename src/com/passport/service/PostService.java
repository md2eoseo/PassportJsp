package com.passport.service;

import com.passport.dao.DBConnection;
import com.passport.vo.PostVO;

public class PostService {

	private static PostService service = new PostService();
	public DBConnection dbcp = DBConnection.getInstance();
	
	private PostService() {}
	public static PostService getInstance() {
		return service;
	}
	
	public int postCreate(PostVO post) {
		return dbcp.postCreate(post);
	}
}
