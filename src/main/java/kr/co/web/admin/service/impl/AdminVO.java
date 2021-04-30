package kr.co.web.admin.service.impl;

import lombok.Data;

public @Data class AdminVO {
	private int seq;
	private String category;
	private String title;
	private String contents;
	private String price;
	private int click_cnt;
	private String delyn;
	private String create_date;
	private String writer;
	private String photo;
	
}
