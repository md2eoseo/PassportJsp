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
	
	public boolean postCreate(PostVO post) {
		return dbcp.postCreate(post);
	}
	
	public PostVO postRead(int board_num) {
		return dbcp.postRead(board_num);
	}
	
	public PostVO postMyRead(int board_num) {
		return dbcp.postMyRead(board_num);
	}
	
	public boolean updateCount(int board_num) {
		return dbcp.updateCount(board_num);
	}
}
