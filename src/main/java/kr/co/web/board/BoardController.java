package kr.co.web.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.web.board.service.BoardService;
import kr.co.web.board.service.impl.BoardVO;
import kr.co.web.board.service.impl.ReplyVO;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
	@Autowired BoardService boardService;
	
	@RequestMapping("/load")
	public String getListAddr() {
		return "jsp/main/BOARD";
	}
	
	@ResponseBody
	@RequestMapping(value = "/load", method=RequestMethod.POST)
	public List<BoardVO> getList(){;
	List<BoardVO> result = boardService.getList();
		return result;
	}
	
	@RequestMapping("/qnaList")
	public String qnaAddr() {
		return "jsp/board/board";
	}
	
	@ResponseBody
	@RequestMapping(value = "/qnaList", method=RequestMethod.POST)
	public List<BoardVO> getQnaList(HttpSession session){;
		List<BoardVO> result = boardService.getList();
		String chkErr = (String) session.getAttribute("boardErr");
		
		if(chkErr != "") {
			session.removeAttribute("boardErr");
		}
	
		return result;
	}
	
	@RequestMapping("/writeQna")
	public String writeQnaAddr(HttpSession session) {
		String result = "";
		String loginedId = (String) session.getAttribute("u_id");
		try {
			if(!loginedId.equals("")) {
				result = "jsp/board/writeQna";
			}
			
		}catch(Exception e) {
			result = "redirect:/login";
		}
		return result;
	}
	
	@RequestMapping(value="/writeQna", method=RequestMethod.POST)
	public String writeQna(BoardVO vo, HttpSession session) {
		String u_id = (String) session.getAttribute("u_id");
		vo.setU_id(u_id);
		if(vo.getTitle() != null && vo.getContents() != null) {
			boardService.writeQna(vo);
		}
		return "jsp/board/board";
	}
	
	@RequestMapping("/qnaDetail")
	public String detail(Model model,String seq, HttpServletRequest request) {
		String result = "";
		
		HttpSession session = request.getSession();
		BoardVO data = boardService.getDetail(seq);
		
		try {
			String userId = (String) session.getAttribute("u_id");
			String writerId = boardService.getWriterId(seq);
			
			String u_code = (String) session.getAttribute("u_code");
			if(userId == null){
				result = "jsp/member/login";
			}
			if(userId.equals(writerId) || u_code.equals("A")) {
				model.addAttribute("data", data);
				result = "jsp/board/qnaDetail";
			}
			else {
				session.setAttribute("boardErr", "작성자와 회원 아이디가 다릅니다.");
				result = qnaAddr();
			}
		} catch(Exception e) {}
		
		
		return result;
	}
}
