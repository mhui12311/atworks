package kr.co.web.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.web.admin.service.AdminService;
import kr.co.web.admin.service.impl.AdminVO;
import kr.co.web.board.service.BoardService;
import kr.co.web.board.service.impl.BoardVO;
import kr.co.web.board.service.impl.ReplyVO;
import kr.co.web.member.service.MemberService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	AdminService ad;
	@Autowired
	MemberService memberService;
	@Autowired
	BoardService boardService;

	// String dir =
	// "C:/work/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/code_spring_single/WEB-INF/views/images/product";
//	String dir = "C:/DEV/deploy/wtpwebapps/code_spring_single/WEB-INF/views/images/product"; // 김명회 workspace 경로. 작업시 경로
																								// 자기거에 맞게 변경해야함
	String dir = "\\\\192.168.0.7\\setting\\image";

	@RequestMapping(value = "/operator_main")
	public String adMain() {
		
		
		
		return "jsp/operator/operator_main";
	}

	@RequestMapping(value = "/ad_member")
	public String adMember() {
		return "jsp/admin/ad_member";
	}

	@RequestMapping(value = "/product_reg")
	public String adProduct_reg() {

		return "jsp/main/product_reg";
	}

	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String adProduct_insert(AdminVO vo, @RequestParam(value = "photoo") MultipartFile file, HttpServletRequest request) {
//		String dir2 = "C:/Users/minwl/git/atworksMVCsingle/code_spring_single/src/main/webapp/WEB-INF/views/images/product";
		String name = file.getOriginalFilename();
		
		try {
			file.transferTo(new File(dir + "/" + name));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

		vo.setPhoto(name);
		int cnt = ad.product_insert(vo);
		if (cnt == 1) {
			System.out.println("상품 1건 등록 완료");
		}
		return "jsp/operator/operator_main";
	}

	/*
	 * @RequestMapping(value = "/regist", method = RequestMethod.POST) public String
	 * adProduct_insert(AdminVO vo, MultipartHttpServletRequest mtfRequest) { int
	 * cnt = 0; List<MultipartFile> fileList = mtfRequest.getFiles("photoo"); String
	 * src = mtfRequest.getParameter("src"); System.out.println("src value : "+src);
	 * List<String> files = new ArrayList<>(); for(MultipartFile mf : fileList) {
	 * String originName = mf.getOriginalFilename(); if (cnt == 0)
	 * vo.setPhoto(originName); System.out.println("originName : "+originName); try
	 * { mf.transferTo(new File(dir + "/" + originName)); files.add(originName); }
	 * catch(Exception e) {}
	 * 
	 * }
	 * 
	 * return "jsp/operator/operator_main"; }
	 */
	@RequestMapping(value = "/productList")
	public String nnew(Model model) {
		List<AdminVO> list = ad.product_select();

		model.addAttribute("list", list);

		return "jsp/admin/productList";
	}

	@RequestMapping(value = "/detail")
	public String detail(Model model, String seq) {
		AdminVO vo = ad.product_detail(seq);

		model.addAttribute("detail", vo);

		return "jsp/admin/detail";
	}

	@RequestMapping(value = "/update")
	public String update(AdminVO vo, @RequestParam(value = "photoo") MultipartFile file) {
		String name = file.getOriginalFilename();
		if (name != "") {
			try {
				file.transferTo(new File(dir + "/" + name));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			vo.setPhoto(name);
		}
		ad.product_update(vo);

		return "redirect:/admin/detail?seq=" + vo.getSeq();
	}

	@RequestMapping(value = "/delete")
	public String update(Model model, String seq) {
		AdminVO vo = ad.product_detail(seq);

		model.addAttribute("detail", vo);

		return "jsp/admin/detail";
	}

	@RequestMapping("/ad_BOARD")
	public String boardAddr() {
		return "jsp/admin/ad_BOARD";
	}
	
	@RequestMapping("/ad_qnaDetail")
	public String detail(Model model,String seq, HttpSession session) {
		String result = "";
		
		BoardVO data = boardService.getDetail(seq);
		session.setAttribute("seq", seq);
		try {
			model.addAttribute("data", data);
			result = "jsp/admin/ad_qnaDetail";
		} catch(Exception e) {}
		
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/response", method=RequestMethod.POST)
	public int responseQna(BoardVO vo,ReplyVO replyVO, HttpSession session) {
		if(session.getAttribute("responseResult") != null) {
			session.removeAttribute("responseResult");
		}
		String temp = (String) session.getAttribute("seq");
		int seq = Integer.parseInt(temp);
		String u_id = (String) session.getAttribute("u_id");
		
		vo.setSeq(seq);
		replyVO.setQna_seq(seq);
		replyVO.setU_id(u_id);
		System.out.println(vo.getSeq()+", "+replyVO.getReply_contents());
		int result = boardService.writeReply(replyVO);
		
		if(result != 0) {
			session.setAttribute("responseResult", "답변이 완료되었습니다.");
			
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/getReply", method=RequestMethod.POST)
	public String getResponseContents(HttpSession session) {
		String seq = (String) session.getAttribute("seq");
		String reply_contents = "";
		try {
			reply_contents = boardService.getReply(seq).getReply_contents();
		} catch(Exception e) {}
		
		
		return reply_contents;
	}
}
