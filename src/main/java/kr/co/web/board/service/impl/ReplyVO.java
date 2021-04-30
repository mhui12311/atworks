package kr.co.web.board.service.impl;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

public @Data class ReplyVO {
	private int qna_seq;
	private int reply_seq;
	private String u_id;
	private String reply_contents;
	private String category;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
	private Date create_date;
}
