package kr.co.web.member.service.impl;

import lombok.Data;

public @Data class MemberVO {
	private String u_id;
	private String u_pwd;
	private String u_name;
	private String u_tel;
	private String u_birth;
	private String u_wedding;
	private String u_p_birth;
	private String u_email;
	private String u_address;
	private String u_start_date;
	private String u_code;
	private String u_authKey;
	private String keyword;
}
