package com.passport.dao;

import java.sql.Connection;
// import java.sql.DriverManager; jdbc 프로그램으로 구현했을 때 사용
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.passport.vo.MemberVO;
 
public class DBConnection 
{
    private static DBConnection dbcp = new DBConnection();
	private DBConnection() { }
	public static DBConnection getInstance() {
		return dbcp;
	}
	
	public static Connection getConnection() throws SQLException, NamingException, ClassNotFoundException {
        /*Context initCtx = new InitialContext();
        
        // initCtx의 lookup메서드를 이용해서 "java:comp/env" 에 해당하는 객체를 찾아서 evnCtx에 삽입
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        
        
        // envCtx의 lookup메서드를 이용해서 "jdbc/orcl"에 해당하는 객체를 찾아서 ds에 삽입
        DataSource ds = (DataSource) envCtx.lookup("jdbc/orcl");
        
        // getConnection메서드를 이용해서 커넥션 풀로 부터 커넥션 객체를 얻어내어 conn변수에 저장
        Connection conn = ds.getConnection();
        return conn;*/
        
        Context context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/xe");
        Connection conn = dataSource.getConnection(); 
        return conn;
        
	}
	
//	public Connection connect() {
//		Connection conn = null;
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
//		} catch (Exception ex) {
//			System.out.println("Error : " + ex);
//		}
//		return conn;
//	}
//	
//	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
//		if(rs != null) {
//			try {
//				rs.close();
//			} catch (Exception ex) {
//				System.out.println("Error : rs.close() " + ex);
//			}
//		}
//		close(conn, ps);
//	}
//	
//	public void close(Connection conn, PreparedStatement ps) {
//		if(ps != null) {
//			try {
//				ps.close();
//			} catch (Exception ex) {
//				System.out.println("Error : ps.close() " + ex);
//			}
//		}
//		if(conn != null) {
//			try {
//				conn.close();
//			} catch (Exception ex) {
//				System.out.println("Error : conn.close() " + ex);
//			}
//		}
//	}
	
	public void memberInsert(MemberVO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into member values(?,?,?,?)");
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getMail());
			pstmt.executeUpdate();			
		} catch (Exception ex) {
			System.out.println("Error : " + ex);
		}
	}
	
	public MemberVO memberSearch(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MemberVO member = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * form member where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new MemberVO();
				member.setId(rs.getString(1));
				member.setPassword(rs.getString(2));
				member.setName(rs.getString(3));
				member.setMail(rs.getString(4));
			}
		} catch (Exception ex) {
			System.out.println("Error : " + ex);
		}
		
		return member;
	}
	
	public void memberUpdate(MemberVO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update member set password=?, name=?, mail=? where id=?");
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getMail());
			pstmt.setString(4, member.getId());
			pstmt.executeUpdate();			
		} catch (Exception ex) {
			System.out.println("Error : " + ex);
		}
	}
	
	public void memberDelete(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from member where id=?");
			pstmt.setString(1, id);
			pstmt.executeUpdate();			

		} catch (Exception ex) {
			System.out.println("Error : " + ex);
		}
	}
	
	public int memberLogin(String id, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbPW;
		int x = -1;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from member where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dbPW = rs.getString("password");
				if(dbPW.equals(password)) {
					System.out.println("비밀번호 일치");
					x = 1;
				} else {
					System.out.println("비밀번호 불일치");
					x = 0;
				}
			} else {
				System.out.println("존재하지 않는 아이디 입력");
				x = -1;
			}
			
		} catch (Exception ex) {
			System.out.println("Error : " + ex);
		}
		
		return x;
	}
	
}