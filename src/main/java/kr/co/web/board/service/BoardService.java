package kr.co.web.board.service;

import java.util.List;

import kr.co.web.board.service.impl.BoardVO;
import kr.co.web.board.service.impl.ReplyVO;

public interface BoardService {
	List<BoardVO> getList();
	int writeQna(BoardVO vo);
	BoardVO getDetail(String seq);
	String getWriterId(String seq);
	int writeReply(ReplyVO vo);
	ReplyVO getReply(String qna_seq);
}
