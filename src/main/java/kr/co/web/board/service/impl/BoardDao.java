package kr.co.web.board.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {
	@Autowired private SqlSession sqlSession;
	
	public List<BoardVO> selectList(){
		return sqlSession.selectList("board.selectList");
	}
	
	public int insertQna(BoardVO vo) {
		return sqlSession.insert("board.insertQna",vo);
	}
	
	public BoardVO selectDetail(String seq) {
		return sqlSession.selectOne("board.selectDetail", seq);
	}
	
	public String selectWriterId(String seq) {
		return sqlSession.selectOne("board.selectWriterId", seq);
	}
	
	public int insertReply(ReplyVO vo) {
		return sqlSession.insert("board.insertReply", vo);
	}
	
	public ReplyVO selectReply(String qna_seq) {
		return sqlSession.selectOne("board.selectReply", qna_seq);
	}
}
