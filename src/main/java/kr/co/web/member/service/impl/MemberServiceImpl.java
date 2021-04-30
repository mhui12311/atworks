package kr.co.web.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.web.member.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired MemberDao dao;
	@Autowired BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public int check_id(String id) {
		int cnt = dao.check_id(id);
		return cnt;
	}
	
	@Override
	public int insert_id(MemberVO vo) {
		String encpass = bCryptPasswordEncoder.encode(vo.getU_pwd());
		System.out.println(encpass);
		vo.setU_pwd(encpass);
		int cnt = dao.insert_id(vo);
		return cnt;
	}
	
	@Override
	public int check_login(MemberVO vo) {
		int cnt = 0;
		MemberVO jvo = dao.get_id(vo);
		if (jvo.getU_pwd() != null && bCryptPasswordEncoder.matches(vo.getU_pwd(), jvo.getU_pwd())) {
			cnt = 1;
		}
		
		return cnt;
	}
	
	@Override
	public String getU_code(String u_id) {
		String u_code = dao.getU_code(u_id);
		return u_code;
	}
	
	@Override
	public List<MemberVO> getList() {
		return dao.selectList();
	}

	
	@Override
	public int memberUpdate(MemberVO vo) {
		return dao.memberUpdate(vo);
	}
	@Override
	public int memberPwdUpdate(MemberVO vo) {
		String encpass = bCryptPasswordEncoder.encode(vo.getU_pwd());
		System.out.println(encpass);
		vo.setU_pwd(encpass);
		int cnt = dao.memberPwdUpdate(vo);
		return cnt;
	}

	@Override
	public int memberDelete(MemberVO vo) {
		return dao.memberDelete(vo);
	}

	@Override
	public MemberVO selectInfo(String u_id) {
		return dao.selectInfo(u_id);
	}
	
	public void findPwd(MemberVO vo) {
		
	}

	@Override
	public int getMemberCount() {
		return dao.selectMemberCount();
	}

	@Override
	public List<MemberVO> findMember(String keyword) {
		return dao.searchMember(keyword);
	}
}
