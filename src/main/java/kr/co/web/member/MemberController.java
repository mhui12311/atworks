package kr.co.web.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.web.admin.service.AdminService;
import kr.co.web.admin.service.impl.AdminVO;
import kr.co.web.member.service.MemberService;
import kr.co.web.member.service.impl.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	MemberService memberService;
	@Autowired
	AdminService adminService;
	@Autowired
	private JavaMailSender mailSender;

	@RequestMapping(value = "/check_id", method = RequestMethod.POST)
	@ResponseBody
	public int check_id(String u_id) {
		int result = memberService.check_id(u_id);
		return result;
	}

	@RequestMapping(value = "/main2", method = RequestMethod.POST)
	public String insert_id(MemberVO vo) {

		int cnt = memberService.insert_id(vo);

		if (cnt == 1) {
			System.out.println("회원 1건 등록 완료");
		}

		return "redirect:/main";
	}

	@RequestMapping(value = "/check_login", method = RequestMethod.POST)
	@ResponseBody
	public int login_ok(MemberVO vo, String u_id, String u_pwd, HttpSession session) {
		vo.setU_id(u_id);
		vo.setU_pwd(u_pwd);
		int cnt = 0;
		try {
			cnt = memberService.check_login(vo);
			MemberVO mvo = memberService.selectInfo(u_id);
			if (cnt == 1) {
				System.out.println("정보 일치");
				session.setAttribute("u_code", mvo.getU_code());
				session.setAttribute("member", mvo);
			}
		} catch (Exception e) {
			session.setAttribute("loginErr", "아이디나 비밀번호를 확인하세요.");
		}

		return cnt;
	}

	@RequestMapping(value = "/login_ok")
	public String login_ok(MemberVO vo, HttpSession session) {
		String u_id = vo.getU_id();
		/* String u_code = memberService.getU_code(u_id) */
		session.setAttribute("u_id", u_id);
		/* session.setAttribute("u_code", u_code) */
		return "redirect:/main";
	}

	@RequestMapping(value = "/mailCheck", method = RequestMethod.GET)
	@ResponseBody
	public String mailCheckGET(String u_email) throws Exception {
		System.out.println(u_email);

		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;
		System.out.println(checkNum);

		String setFrom = "ckddnd1995@naver.com";
		String toMail = u_email;
		String title = "AT ShopingMall 인증 이메일입니다";
		String content = "인증번호는 " + checkNum + " 입니다.";

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content, true);
			mailSender.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
		String num = Integer.toString(checkNum);

		return num;
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("u_id");
		session.removeAttribute("u_code");
		return "redirect:/main";
	}

	@RequestMapping("/productList")
	public String getProductAddr(Model model) {
		System.out.println("리스트 불러옴");
		List<AdminVO> list = adminService.product_select();

		model.addAttribute("list", list);
		return "/jsp/member/productList";
	}

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public List<MemberVO> getList() {
		return memberService.getList();
	}

	@RequestMapping(value = "/memberUpdate.do")
	public String MemberUpdate(@RequestBody MemberVO vo, HttpSession session) {
		MemberVO mvo = (MemberVO) session.getAttribute("member");
		mvo.setU_name(vo.getU_name());
		mvo.setU_email(vo.getU_email());
		mvo.setU_tel(vo.getU_tel());
		mvo.setU_address(vo.getU_address());

		int cnt = memberService.memberUpdate(mvo);

		String msg = "";

		if (cnt == 1) {
			System.out.println("회원수정 완료");
			msg = "<script>alert('회원수정 완료 되었습니다.);location.href='redirect:/main'</script>";
		} else {
			System.out.println("회원수정 실패");
			msg = "<script>alert('회원수정 실패 되었습니다.);location.href='/memberUpdateView'</script>";
		}

		return msg;
	}

	@RequestMapping(value = "/memberDelete.do")
	public String memberDelete(MemberVO vo, RedirectAttributes rttr, HttpSession session) {
		MemberVO mvo = (MemberVO) session.getAttribute("member");
		memberService.memberDelete(mvo);
		session.invalidate();
		return "redirect:/main";
	}

	@RequestMapping(value = "/memberPwdUpdate.do")
	public String MemberPwdUpdate(@RequestBody MemberVO vo, HttpSession session) {
		MemberVO mvo = (MemberVO) session.getAttribute("member");
		mvo.setU_pwd(vo.getU_pwd());

		int cnt = memberService.memberPwdUpdate(mvo);

		if (cnt == 1) {
			System.out.println("회원 비밀번호 수정 완료");
		} else {
			System.out.println("회원 비밀번호 수정 실패");
		}

		return "redirect:/main";
	}

	@RequestMapping(value = "/find_pwd", method = RequestMethod.POST)
	public String findPwd(MemberVO vo, String u_email) {

		return u_email;
	}
	
	@ResponseBody
	@RequestMapping(value = "/find_user", method = RequestMethod.POST)
	public List<MemberVO> findUser(String keyword,Model model) {
		List<MemberVO> result = new ArrayList<>();
		if(keyword != "") {
			result = memberService.findMember(keyword);
		} else {
			result = null;
		}
		
		
		return result;
	}

}
