package com.passport.service;

import com.passport.dao.DBConnection;
import com.passport.vo.MemberVO;
import com.passport.vo.PostVO;

public class MemberService {

	private static MemberService service = new MemberService();
	public DBConnection dbcp = DBConnection.getInstance();
	
	private MemberService() {}
	public static MemberService getInstance() {
		return service;
	}
	
	public boolean memberInsert(MemberVO member) {
		return dbcp.memberInsert(member);
	}
	
	public MemberVO memberSearch(String id) {
		MemberVO member = dbcp.memberSearch(id);
		return member;
	}
	
	public void memberUpdate(MemberVO member) {
		dbcp.memberUpdate(member);
	}
	
	public void memberDelete(String id) {
		dbcp.memberDelete(id);
	}
	
	public int memberLogin(String id, String password) {
		return dbcp.memberLogin(id, password);
	}
	
	public int postCreate(PostVO post) {
		return dbcp.postCreate(post);
	}
}
