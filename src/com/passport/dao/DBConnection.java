package com.passport.dao;

import java.sql.Connection;
// import java.sql.DriverManager; jdbc 프로그램으로 구현했을 때 사용
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.passport.vo.MemberVO;
import com.passport.vo.PostVO;
 
public class DBConnection 
{
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
    private static DBConnection dbcp = new DBConnection();
	private DBConnection() { }
	public static DBConnection getInstance() {
		return dbcp;
	}
	
	public static Connection getConnection() throws SQLException, NamingException, ClassNotFoundException {
//        Context initCtx = new InitialContext();
//        // initCtx의 lookup메서드를 이용해서 "java:comp/env" 에 해당하는 객체를 찾아서 evnCtx에 삽입
//        Context envCtx = (Context) initCtx.lookup("java:comp/env");
//        // envCtx의 lookup메서드를 이용해서 "jdbc/orcl"에 해당하는 객체를 찾아서 ds에 삽입
//        DataSource ds = (DataSource) envCtx.lookup("jdbc/orcl");
//        // getConnection메서드를 이용해서 커넥션 풀로 부터 커넥션 객체를 얻어내어 conn변수에 저장
//        Connection conn = ds.getConnection();
//        return conn;
        
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
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into member values(?,?,?,?)");
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getMail());
			pstmt.executeUpdate();	
			close();
		} catch (Exception ex) {
			System.out.println("Error : " + ex);
		}
	}
	
	public MemberVO memberSearch(String id) {
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
			close();
		} catch (Exception ex) {
			System.out.println("Error : " + ex);
		}
		
		return member;
	}
	
	public void memberUpdate(MemberVO member) {
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update member set password=?, name=?, mail=? where id=?");
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getMail());
			pstmt.setString(4, member.getId());
			pstmt.executeUpdate();
			close();
		} catch (Exception ex) {
			System.out.println("Error : " + ex);
		}
	}
	
	public void memberDelete(String id) {
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from member where id=?");
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			close();
		} catch (Exception ex) {
			System.out.println("Error : " + ex);
		}
	}
	
	public int memberLogin(String id, String password) {
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
		close();
		return x;
	}
	
	// post service
	public int getSeq(){
        int result = 1;
        
        try {
            conn = DBConnection.getConnection();
            
            // 시퀀스 값을 가져온다. (DUAL : 시퀀스 값을 가져오기위한 임시 테이블)
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT BOARD_NUM.NEXTVAL FROM DUAL");
            
            pstmt = conn.prepareStatement(sql.toString());

            rs = pstmt.executeQuery();
            
            if(rs.next())
            	result = rs.getInt(1);
 
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        close();
        return result;    
    }
	
	public int postCreate(PostVO post) {
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into post values(?,?,?,?,?,?,?,?,?,sysdate)");
			pstmt.setInt(1, getSeq());
			pstmt.setString(2, post.getBoard_id());
			pstmt.setString(3, post.getBoard_subject());
			pstmt.setString(4, post.getBoard_content());
			pstmt.setString(5, post.getBoard_file());
            pstmt.setString(6, "test");
            pstmt.setInt(7, 0);
            pstmt.setInt(8, 0);
            pstmt.setInt(9, 0);
			pstmt.executeUpdate();
			close();
			return 1;
		} catch (Exception ex) {
			System.out.println("Error : " + ex);
			close();
			return -1;
		}
	}
	
	public ArrayList<PostVO> postList(HashMap<String, Object> listOpt){
        ArrayList<PostVO> list = new ArrayList<PostVO>();
        
        String opt = (String)listOpt.get("opt");
        String condition = (String)listOpt.get("condition");
        int start = (Integer)listOpt.get("start");
        
        try {
            conn = DBConnection.getConnection();
            StringBuffer sql = new StringBuffer();
            
            // 글 전체
            if(opt == null){
                sql.append("select * from ");
                sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
                sql.append(", BOARD_CONTENT, BOARD_FILE, BOARD_COUNT, BOARD_GROUP");
                sql.append(", BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_DATE ");
                sql.append("FROM");
                sql.append(" (select * from POST order by BOARD_DATE desc, BOARD_NUM desc)) ");
                sql.append("where rnum>=? and rnum<=?");
                
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setInt(1, start);
                pstmt.setInt(2, start+9);

                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("0")) // 제목으로 검색
            {
                sql.append("select * from ");
                sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
                sql.append(", BOARD_CONTENT, BOARD_FILE, BOARD_DATE, BOARD_COUNT");
                sql.append(", BOARD_GROUP, BOARD_RE_LEV, BOARD_RE_SEQ ");
                sql.append("FROM ");
                sql.append("(select * from POST where BOARD_SUBJECT like ? ");
                sql.append("order BY BOARD_DATE desc, BOARD_NUM desc)) ");
                sql.append("where rnum>=? and rnum<=?");
                
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, "%"+condition+"%");
                pstmt.setInt(2, start);
                pstmt.setInt(3, start+9);
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("1")) // 내용으로 검색
            {
                sql.append("select * from ");
                sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
                sql.append(", BOARD_CONTENT, BOARD_FILE, BOARD_DATE, BOARD_COUNT");
                sql.append(", BOARD_GROUP, BOARD_RE_LEV, BOARD_RE_SEQ ");
                sql.append("FROM ");
                sql.append("(select * from POST where BOARD_CONTENT like ? ");
                sql.append("order BY BOARD_DATE desc, BOARD_NUM desc)) ");
                sql.append("where rnum>=? and rnum<=?");
                
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, "%"+condition+"%");
                pstmt.setInt(2, start);
                pstmt.setInt(3, start+9);
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("2")) // 제목+내용으로 검색
            {
                sql.append("select * from ");
                sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
                sql.append(", BOARD_CONTENT, BOARD_FILE, BOARD_DATE, BOARD_COUNT");
                sql.append(", BOARD_GROUP, BOARD_RE_LEV, BOARD_RE_SEQ ");
                sql.append("FROM ");
                sql.append("(select * from POST where BOARD_SUBJECT like ? OR BOARD_CONTENT like ? ");
                sql.append("order BY BOARD_DATE desc, BOARD_NUM desc)) ");
                sql.append("where rnum>=? and rnum<=?");
                
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, "%"+condition+"%");
                pstmt.setString(2, "%"+condition+"%");
                pstmt.setInt(3, start);
                pstmt.setInt(4, start+9);
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("3")) // 글쓴이로 검색
            {
                sql.append("select * from ");
                sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
                sql.append(", BOARD_CONTENT, BOARD_FILE, BOARD_DATE, BOARD_COUNT");
                sql.append(", BOARD_GROUP, BOARD_RE_LEV, BOARD_RE_SEQ ");
                sql.append("FROM ");
                sql.append("(select * from POST where BOARD_ID like ? ");
                sql.append("order BY BOARD_DATE desc, BOARD_NUM desc)) ");
                sql.append("where rnum>=? and rnum<=?");
                
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, "%"+condition+"%");
                pstmt.setInt(2, start);
                pstmt.setInt(3, start+9);
                
                sql.delete(0, sql.toString().length());
            }
            
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                PostVO post = new PostVO();
                post.setBoard_num(rs.getInt("BOARD_NUM"));
                post.setBoard_id(rs.getString("BOARD_ID"));
                post.setBoard_subject(rs.getString("BOARD_SUBJECT"));
                post.setBoard_content(rs.getString("BOARD_CONTENT"));
                post.setBoard_file(rs.getString("BOARD_FILE"));
                post.setBoard_count(rs.getInt("BOARD_COUNT"));
                post.setBoard_group(rs.getString("BOARD_GROUP"));
                post.setBoard_re_lev(rs.getInt("BOARD_RE_LEV"));
                post.setBoard_re_seq(rs.getInt("BOARD_RE_SEQ"));
                post.setBoard_date(rs.getDate("BOARD_DATE"));
                list.add(post);
            }
            
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        close();
        return list;
    } 
	
    public int getPostListCount(HashMap<String, Object> listOpt){
        int result = 0;
        String opt = (String)listOpt.get("opt");
        String condition = (String)listOpt.get("condition");

        try {
            conn = DBConnection.getConnection();
            StringBuffer sql = new StringBuffer();
            
            if(opt == null)    // 전체글의 개수
            {
                sql.append("select count(*) from POST");
                pstmt = conn.prepareStatement(sql.toString());

                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("0")) // 제목으로 검색한 글의 개수
            {
                sql.append("select count(*) from POST where BOARD_SUBJECT like ?");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("1")) // 내용으로 검색한 글의 개수
            {
                sql.append("select count(*) from POST where BOARD_CONTENT like ?");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("2")) // 제목+내용으로 검색한 글의 개수
            {
                sql.append("select count(*) from POST ");
                sql.append("where BOARD_SUBJECT like ? or BOARD_CONTENT like ?");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, '%'+condition+'%');
                pstmt.setString(2, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("3")) // 글쓴이로 검색한 글의 개수
            {
                sql.append("select count(*) from POST where BOARD_ID like ?");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, '%'+condition+'%');
                
                sql.delete(0, sql.toString().length());
            }
            
            rs = pstmt.executeQuery();
            if(rs.next())    result = rs.getInt(1);
            
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        close();
        return result;
    }
    
    public PostVO postRead(int board_num) {
    	PostVO post = null;
        
        try {
            conn = DBConnection.getConnection();
            
            StringBuffer sql = new StringBuffer();
            sql.append("select * from POST where BOARD_NUM = ?");
            
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setInt(1, board_num);
            
            rs = pstmt.executeQuery();
            if(rs.next()) {
            	post = new PostVO();
            	post.setBoard_num(board_num);
            	post.setBoard_id(rs.getString("BOARD_ID"));
            	post.setBoard_subject(rs.getString("BOARD_SUBJECT"));
            	post.setBoard_content(rs.getString("BOARD_CONTENT"));
            	post.setBoard_file(rs.getString("BOARD_FILE"));
            	post.setBoard_count(rs.getInt("BOARD_COUNT"));
            	post.setBoard_group(rs.getString("BOARD_GROUP"));
            	post.setBoard_re_lev(rs.getInt("BOARD_RE_LEV"));
            	post.setBoard_re_seq(rs.getInt("BOARD_RE_SEQ"));
            	post.setBoard_date(rs.getDate("BOARD_DATE"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        close();
        return post;
    }
    
    public boolean updateCount(int board_num) {
        boolean result = false;
        
        try {
            conn = DBConnection.getConnection();
            
            StringBuffer sql = new StringBuffer();
            sql.append("update POST set BOARD_COUNT = BOARD_COUNT+1 ");
            sql.append("where BOARD_NUM = ?");
            
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setInt(1, board_num);
            
            int flag = pstmt.executeUpdate();
            if(flag > 0){
                result = true;
                conn.commit();
            }    
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            throw new RuntimeException(e.getMessage());
        }
        close();
        return result;
    }
    
    public ArrayList<PostVO> postMyList(HashMap<String, Object> listOpt, String userid) {
    	ArrayList<PostVO> list = new ArrayList<PostVO>();
        
        String opt = (String)listOpt.get("opt");
        String condition = (String)listOpt.get("condition");
        int start = (Integer)listOpt.get("start");
        
        try {
            conn = DBConnection.getConnection();
            StringBuffer sql = new StringBuffer();
            
            // 글 전체
            if(opt == null){
                sql.append("select * from ");
                sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
                sql.append(", BOARD_CONTENT, BOARD_FILE, BOARD_COUNT, BOARD_GROUP");
                sql.append(", BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_DATE ");
                sql.append("FROM");
                sql.append(" (select * from POST where BOARD_ID=? order by BOARD_DATE desc, BOARD_NUM desc)) ");
                sql.append("where rnum>=? and rnum<=?");
                
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, userid);
                pstmt.setInt(2, start);
                pstmt.setInt(3, start+9);

                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("0")) // 제목으로 검색
            {
                sql.append("select * from ");
                sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
                sql.append(", BOARD_CONTENT, BOARD_FILE, BOARD_DATE, BOARD_COUNT");
                sql.append(", BOARD_GROUP, BOARD_RE_LEV, BOARD_RE_SEQ ");
                sql.append("FROM ");
                sql.append("(select * from POST where BOARD_SUBJECT like ? and BOARD_ID=?");
                sql.append("order BY BOARD_DATE desc, BOARD_NUM desc)) ");
                sql.append("where rnum>=? and rnum<=?");
                
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, "%"+condition+"%");
                pstmt.setString(2, userid);
                pstmt.setInt(3, start);
                pstmt.setInt(4, start+9);
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("1")) // 내용으로 검색
            {
                sql.append("select * from ");
                sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
                sql.append(", BOARD_CONTENT, BOARD_FILE, BOARD_DATE, BOARD_COUNT");
                sql.append(", BOARD_GROUP, BOARD_RE_LEV, BOARD_RE_SEQ ");
                sql.append("FROM ");
                sql.append("(select * from POST where BOARD_CONTENT like ? and BOARD_ID=?");
                sql.append("order BY BOARD_DATE desc, BOARD_NUM desc)) ");
                sql.append("where rnum>=? and rnum<=?");
                
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, "%"+condition+"%");
                pstmt.setString(2, userid);
                pstmt.setInt(3, start);
                pstmt.setInt(4, start+9);
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("2")) // 제목+내용으로 검색
            {
                sql.append("select * from ");
                sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
                sql.append(", BOARD_CONTENT, BOARD_FILE, BOARD_DATE, BOARD_COUNT");
                sql.append(", BOARD_GROUP, BOARD_RE_LEV, BOARD_RE_SEQ ");
                sql.append("FROM ");
                sql.append("(select * from POST where BOARD_SUBJECT like ? OR BOARD_CONTENT like ? and BOARD_ID=?");
                sql.append("order BY BOARD_DATE desc, BOARD_NUM desc)) ");
                sql.append("where rnum>=? and rnum<=?");
                
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, "%"+condition+"%");
                pstmt.setString(2, "%"+condition+"%");
                pstmt.setString(3, userid);
                pstmt.setInt(4, start);
                pstmt.setInt(5, start+9);
                
                sql.delete(0, sql.toString().length());
            }
            
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                PostVO post = new PostVO();
                post.setBoard_num(rs.getInt("BOARD_NUM"));
                post.setBoard_id(rs.getString("BOARD_ID"));
                post.setBoard_subject(rs.getString("BOARD_SUBJECT"));
                post.setBoard_content(rs.getString("BOARD_CONTENT"));
                post.setBoard_file(rs.getString("BOARD_FILE"));
                post.setBoard_count(rs.getInt("BOARD_COUNT"));
                post.setBoard_group(rs.getString("BOARD_GROUP"));
                post.setBoard_re_lev(rs.getInt("BOARD_RE_LEV"));
                post.setBoard_re_seq(rs.getInt("BOARD_RE_SEQ"));
                post.setBoard_date(rs.getDate("BOARD_DATE"));
                list.add(post);
            }
            
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        close();
        return list;
	}
    
    public int getPostMyListCount(HashMap<String, Object> listOpt, String userid) {
    	int result = 0;
        String opt = (String)listOpt.get("opt");
        String condition = (String)listOpt.get("condition");

        try {
            conn = DBConnection.getConnection();
            StringBuffer sql = new StringBuffer();
            
            if(opt == null)    // 전체글의 개수
            {
                sql.append("select count(*) from POST where BOARD_ID=?");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, userid);

                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("0")) // 제목으로 검색한 글의 개수
            {
                sql.append("select count(*) from POST where BOARD_SUBJECT like ? and BOARD_ID=?");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, '%'+condition+'%');
                pstmt.setString(2, userid);
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("1")) // 내용으로 검색한 글의 개수
            {
                sql.append("select count(*) from POST where BOARD_CONTENT like ? and BOARD_ID=?");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, '%'+condition+'%');
                pstmt.setString(2, userid);
                
                sql.delete(0, sql.toString().length());
            }
            else if(opt.equals("2")) // 제목+내용으로 검색한 글의 개수
            {
                sql.append("select count(*) from POST ");
                sql.append("where BOARD_SUBJECT like ? or BOARD_CONTENT like ? and BOARD_ID=?");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, '%'+condition+'%');
                pstmt.setString(2, '%'+condition+'%');
                pstmt.setString(3, userid);
                
                sql.delete(0, sql.toString().length());
            }
            
            rs = pstmt.executeQuery();
            if(rs.next())    result = rs.getInt(1);
            
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        close();
        return result;
	}
    
    private void close(){
        try {
        	if ( rs != null ){ rs.close(); rs=null;    }
            if ( pstmt != null ){ pstmt.close(); pstmt=null; }
            if ( conn != null ){ conn.close(); conn=null;    }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}