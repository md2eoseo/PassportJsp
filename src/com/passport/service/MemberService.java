package com.passport.service;

import com.passport.dao.DBConnection;
import com.passport.vo.MemberVO;

public class MemberService {

	private static MemberService service = new MemberService();
	public DBConnection dbcp = DBConnection.getInstance();
	
	private MemberService() {}
	public static MemberService getInstance() {
		return service;
	}
	
	public void memberInsert(MemberVO member) {
		dbcp.memberInsert(member);
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
	
	public int memberLogin(MemberVO member) {
		return dbcp.memberLogin(member);
	}
}
