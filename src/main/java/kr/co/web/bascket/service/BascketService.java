package kr.co.web.bascket.service;

import java.util.List;

import kr.co.web.bascket.service.impl.BascketVO;


public interface BascketService {
	
	public List<BascketVO> selectAllBasckets(String u_id);
	
}
