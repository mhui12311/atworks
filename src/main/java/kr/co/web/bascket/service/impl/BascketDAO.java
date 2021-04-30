package kr.co.web.bascket.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.web.board.service.impl.BoardVO;

@Repository
public class BascketDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
//	public List<BoardVO> selectAllBasckets(String u_id){
//		
//	}
	
	
	
}
