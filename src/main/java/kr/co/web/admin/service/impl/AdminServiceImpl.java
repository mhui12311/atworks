package kr.co.web.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.web.admin.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminDAO dao;
	
	@Override
	public int product_insert(AdminVO vo) {
		int cnt = dao. product_insert(vo);
		return cnt;
	}
	
	@Override
	public List<AdminVO> product_select() {
		List<AdminVO> list = dao. product_select();
		return list;
	}

	@Override
	public AdminVO product_detail(String seq) {
		AdminVO vo = dao. product_detail(seq);
		return vo;
	}
	
	@Override
	public void product_update(AdminVO vo) {
		dao.product_update(vo);
	}
}
