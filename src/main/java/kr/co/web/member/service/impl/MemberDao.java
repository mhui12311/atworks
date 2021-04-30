package kr.co.web.member.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {
	@Autowired private SqlSession sqlSession;
	
	public int check_id(String u_id) {
		int cnt = 0;
		cnt = sqlSession.selectOne("member.check_id", u_id);
		return cnt;
	}
	
	public int insert_id(MemberVO vo) {
		int cnt = sqlSession.insert("member.insert_id", vo);
		return cnt;
	}
	
	public MemberVO get_id(MemberVO vo) {
		MemberVO jvo = sqlSession.selectOne("member.get_id", vo);
		return jvo;
	}
	
	public String getU_code(String u_id) {
		MemberVO jvo = sqlSession.selectOne("member.selectUcode", u_id);
		String u_code = jvo.getU_code();
		return u_code;
	}
	public MemberVO selectInfo(String u_id) {
		MemberVO jvo = sqlSession.selectOne("member.selectInfo", u_id);
		return jvo;
	}
	
	public List<MemberVO> selectList(){
		return sqlSession.selectList("member.selectList");
	}
	
	public int memberUpdate(MemberVO vo) {
		return sqlSession.update("member.memberUpdate", vo);
	}
	
	public int memberPwdUpdate(MemberVO vo) {
		return sqlSession.update("member.memberPwdUpdate", vo);
	}
	
	public int memberDelete(MemberVO vo) {
		return sqlSession.delete("member.memberDelete", vo);
	}
	
	public int selectMemberCount() {
		return sqlSession.selectOne("member.selectMemberCount");
	}
	
	public List<MemberVO> searchMember(String keyword) {
		return sqlSession.selectList("member.searchMember", keyword);
	}
}
