package kr.co.web.admin.service;

import java.util.List;

import kr.co.web.admin.service.impl.AdminVO;

public interface AdminService {
	public int product_insert(AdminVO vo);
	public List<AdminVO> product_select();
	public AdminVO product_detail(String seq);
	public void product_update(AdminVO vo);
}
