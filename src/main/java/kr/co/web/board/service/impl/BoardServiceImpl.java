package kr.co.web.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.web.board.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired private BoardDao boardDao;

	@Override
	public List<BoardVO> getList() {
		return boardDao.selectList();
	}

	@Override
	public int writeQna(BoardVO vo) {
		return boardDao.insertQna(vo);
	}

	@Override
	public BoardVO getDetail(String seq) {
		return boardDao.selectDetail(seq);
	}

	@Override
	public String getWriterId(String seq) {
		return boardDao.selectWriterId(seq);
	}

	@Override
	public int writeReply(ReplyVO vo) {
		return boardDao.insertReply(vo);
	}

	@Override
	public ReplyVO getReply(String qna_seq) {
		return boardDao.selectReply(qna_seq);
	}
}
