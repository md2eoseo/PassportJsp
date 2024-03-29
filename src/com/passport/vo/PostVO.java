package com.passport.vo;

import java.sql.Date;

public class PostVO {
	
	private int board_num;          // 번호
    private String board_id;         // 작성자
    private String board_subject;     // 제목
    private String board_content;     // 내용
    private String board_file;         // 첨부파일 이름
    private String board_group;         // 그룹
    private int board_re_lev;         // 댓글 깊이
    private int board_re_seq;         // 댓글 순서
    private int board_count;         // 조회수
    private String board_date;         // 작성일
    private String board_modate;         // 수정일
    private String board_markers;		// 마커
    
    public int getBoard_num() {
        return board_num;
    }
    public void setBoard_num(int board_num) {
        this.board_num = board_num;
    }
    public String getBoard_id() {
        return board_id;
    }
    public void setBoard_id(String board_id) {
        this.board_id = board_id;
    }
    public String getBoard_subject() {
        return board_subject;
    }
    public void setBoard_subject(String board_subject) {
        this.board_subject = board_subject;
    }
    public String getBoard_content() {
        return board_content;
    }
    public void setBoard_content(String board_content) {
        this.board_content = board_content;
    }
    public String getBoard_file() {
        return board_file;
    }
    public void setBoard_file(String board_file) {
        this.board_file = board_file;
    }
    public String getBoard_group() {
        return board_group;
    }
    public void setBoard_group(String board_group) {
        this.board_group = board_group;
    }
    public int getBoard_re_lev() {
        return board_re_lev;
    }
    public void setBoard_re_lev(int board_re_lev) {
        this.board_re_lev = board_re_lev;
    }
    public int getBoard_re_seq() {
        return board_re_seq;
    }
    public void setBoard_re_seq(int board_re_seq) {
        this.board_re_seq = board_re_seq;
    }
    public int getBoard_count() {
        return board_count;
    }
    public void setBoard_count(int board_count) {
        this.board_count = board_count;
    }
    public String getBoard_date() {
        return board_date;
    }
    public void setBoard_date(String board_date) {
        this.board_date = board_date;
    }
	public String getBoard_modate() {
		return board_modate;
	}
	public void setBoard_modate(String board_modate) {
		this.board_modate = board_modate;
	}
	public String getBoard_markers() {
		return board_markers;
	}
	public void setBoard_markers(String board_markers) {
		this.board_markers = board_markers;
	}
	
}
