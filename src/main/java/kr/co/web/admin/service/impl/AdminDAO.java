package kr.co.web.admin.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDAO {
	
	@Autowired
	private SqlSession sqlsession;
	
	public int product_insert(AdminVO vo) {
		int cnt = sqlsession.insert("product.insert_p", vo);
		return cnt;
	}

	public List<AdminVO> product_select() {
		List<AdminVO> list = sqlsession.selectList("product.select_p");
		return list;
	}
	
	public AdminVO product_detail(String seq) {
		AdminVO vo = (AdminVO) sqlsession.selectOne("product.detail_p", seq);
		return vo;
	}
	
	public void product_update(AdminVO vo) {
		sqlsession.update("product.update_p", vo);
	}
}
