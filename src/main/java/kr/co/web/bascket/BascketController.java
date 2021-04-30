package kr.co.web.bascket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.web.bascket.service.impl.BascketServiceImpl;

@Controller
public class BascketController {
	
	@Autowired
	BascketServiceImpl service;
	
	@RequestMapping("/SHOPPING_BASKET")
	public String basket(Model model) {
		
		
		
		return "jsp/member/SHOPPING_BASKET";
	}
	
	@RequestMapping("/insertCart")
	public String aa() {
		
		return "";
	}
	
	

	
	
	

}
