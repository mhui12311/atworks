package kr.co.web.member.service;

import java.util.List;

import kr.co.web.member.service.impl.MemberVO;

public interface MemberService {
	public int check_id(String id);

	public int insert_id(MemberVO vo);

	public int check_login(MemberVO vo);

	public String getU_code(String u_id);

	public MemberVO selectInfo(String u_id);

	List<MemberVO> getList();

	public int memberUpdate(MemberVO vo);//회원 프로필 수정

	public int memberPwdUpdate(MemberVO vo);//비밀번호 수정

	public int memberDelete(MemberVO vo);//회원탈퇴
	
	public void findPwd(MemberVO vo);
	
	int getMemberCount();
	
	List<MemberVO> findMember(String keyword);
}
