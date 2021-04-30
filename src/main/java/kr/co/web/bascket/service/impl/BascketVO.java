package kr.co.web.bascket.service.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BascketVO {
	
	private String u_id;
	private int product_seq;
	private String product_title;
	private int price;
	private int product_count;
	private int total_price;
	private int shipping_price;
	

}
