package kr.co.web.board.service.impl;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

public @Data class BoardVO {
	private int seq;
	private String category;
	private String u_id;
	private String title;
	private String contents;
	private int view_count;
	private String photo;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
	private Date create_date;
	private String response_yn;
	private String response_contents;
}
