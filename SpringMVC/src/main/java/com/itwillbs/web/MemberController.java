package com.itwillbs.web;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.service.MemberService;
// @RequestMapping(value = "/member/*")
// URI가 /member/~ 시작하는 모든 주소를 처리(매핑) ~.me, ~.bo
// GET/POST 방식 상관 없이 모두 처리 가능

@Controller
@RequestMapping(value = "/member/*")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	// 서비스 객체를 주입
	@Inject
	private MemberService mService;
	
//	@Inject
//	private MemberDAO mDao;
	// 주입은 가능하지만 권장하지 X (SPRING.txt 160번 라인)
	
	// http://localhost:8088/web/dbtime
	// http://localhost:8088/web/member/dbtime
	// http://localhost:8088/member/dbtime
	@RequestMapping(value = "/dbtime", method = RequestMethod.GET)
	public void showDbTime(Model model) {
		// 디비의 서버시간정보 조회 -> 연결된 뷰페이지에 출력
		logger.debug(" 서버 시간 : "+mService.getDbServerTime());
		
		String serverTime = mService.getDbServerTime();
		
		// 뷰페이지로 전달할 정보를 저장
		model.addAttribute("serverTime", serverTime);
		
		// 연결된 뷰페이지  /WEB-INF/views/dbtime.jsp
		
	}
	
	// http://localhost:8088/member/insert
	// 회원가입 - 회원정보 입력(GET - 정보 보여주는 형태)
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insertGET() {
		logger.debug(" /insert -> insertGET() 호출 ");
		// 연결된 뷰페이지 실행 /WEB-INF/views/member/insert.jsp
		logger.debug("/views/member/insert.jsp 페이지 연결");
	}
	
	
	// 회원가입 - 입력받은 정보 처리(POST - 정보 처리하는 형태)
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertPOST(/* @ModelAttribute("vo") */ MemberVO vo) {
		logger.debug(" /insert -> insertPOST() 호출 ");
		// 한글 인코딩 => web.xml에 필터를 사용해서 처리
//		requset.setCharacterEncoding(); (x)
		
		// 전달정보를 저장(파라메터 - 아이디, 비밀번호, 이름, 이메일)
		// => 컨트롤러가 자동으로 파라메터 수집
		logger.debug(" vo : "+ vo);
		
		// 서비스 => DB에 회원가입 처리 메서드 호출
		mService.memberJoin(vo);
		
		// 연결된 뷰페이지 실행(페이지 이동) -> 로그인 페이지		
		return "redirect:/member/login";
	}
	
	// http://localhost:8088/member/login
	// 로그인 - 사용자의 아이디, 비밀번호 입력
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGET() {
		logger.debug(" /login -> loginGET() 호출 ");
		
		return "/member/login";
	}
	
	// 로그인 - 입력받은 아이디, 비밀번호를 사용해서 확인
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loingPOST(HttpSession session,MemberVO vo /* @ModelAttribute("userid") String userid */) {
		logger.debug(" /login -> loginPOST() 호출 ");
		// 한글 처리 인코딩 (생략 - 한글처리 필터)
		// 전달한 정보 저장 (아이디, 비밀번호)
		logger.debug(" 로그인 정보 : "+vo);
		
		// 로그인 여부 판단 -> 서비스 -> DAO 로그인 여부 판단
		MemberVO resultVO = mService.memberLogin(vo);
		
		logger.debug(" (～￣▽￣)～ 로그인 결과 : "+resultVO);
		
		
/*		String resultURI = "";
		if(resultVO != null) {
			logger.debug(" 로그인 성공! -> Main 페이지로 이동 ");
//			return "redirect:/member/main";
			resultURI = "redirect:/member/main";
		} else {
			logger.debug(" 로그인 실패 (┬┬﹏┬┬) -> Login 페이지로 이동 ");
//			return "redirect:/member/login";
			resultURI = "redirect:/member/login";
		} */
		
		if(resultVO == null) {
			logger.debug(" 로그인 실패 (┬┬﹏┬┬) ");
			return "redirect:/member/login";
		}
		
		// 로그인 성공!
		// 세션(영역)에 아이디를 저장
		session.setAttribute("id", resultVO.getUserid());
		
		return "redirect:/member/main";
		
	}
	
	// 메인 페이지
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void mainGET(HttpSession session, Model model) {
		logger.debug(" /main -> mainGET() 호출 ");
//		String id = (String)session.getAttribute("id");
//		model.addAttribute("id", id);
		logger.debug(" /member/main.jsp 페이지 이동 ");
	}
	
	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutGET(HttpSession session) {
		logger.debug(" /logout -> logoutGET() 호출 ");
		
		// 세션에 있는 로그인 정보 초기화
		session.invalidate();
		
		logger.debug(" 로그아웃 성공! ヾ(•ω•`)o ");
		
		// 다시 메인페이지로 이동
		return "redirect:/member/main";
	}
	
	// 회원정보 조회
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String infoGET(HttpSession session, Model model) {
		logger.debug(" /info -> infoGET() 호출 ");
		String id = (String)session.getAttribute("id");
		
		// 서비스 -> DAO 회원정보 조회
		MemberVO vo = mService.memberInfo(id);
		logger.debug(" o((>ω< ))o 회원정보 조회 : "+vo);
		
		// 연결된 뷰페이지(/member/info.jsp) 에 정보 전달
		model.addAttribute(vo);
		// => 이름이 없는 경우 전달하는 데이터 타입의 첫글자를 소문자로 변경 후 이름으로 사용
		
		// 뷰페이지 이동
		return "/member/info";
	}
	
	// 회원정보 수정 - 기존의 회원정보를 화면에 보여줌, 변경할 내용 입력 
	@GetMapping(value="/update")
	public void updateGET(HttpSession session, Model model) {		
		logger.debug(" /update -> updateGET() 호출 ");
		
		// id 정보를 가져오기
		String id = (String)session.getAttribute("id");
		
		// 서비스 -> DAO 회원정보 조회
		MemberVO vo = mService.memberInfo(id);
		
		// 연결된 뷰페이지로 정보 전달
		model.addAttribute(vo);
		
		// 연결된 뷰페이지(/member/update.jsp)에 출력
	}
	
	// 회원정보 수정 - 변경된 내용을 DB에 전달해서 수정
	@PostMapping(value="/update")
	public String updatePOST(MemberVO uvo) {
		logger.debug(" /update -> updatePOST() 호출 ");
		
		// 수정할 회원정보를 저장
		logger.debug(" uvo : "+uvo);
		
		// 서비스 -> DAO 회원정보 수정
		mService.memberUpdate(uvo);
		
		logger.debug(" (～￣▽￣)～ 회원정보 수정 완료 ~ ");
		
		return "redirect:/member/main";
	}
	
	// 회원정보 삭제 - 사용자의 비밀번호 입력(+아이디 세션)
	@GetMapping(value="/delete")
	public void deleteGET() {
		logger.debug(" /delete -> deleteGET() 호출 ");
		logger.debug(" /member/delete.jsp 페이지 연결 ");
	
	}
	
	
	// 회원정보 삭제 - 전달받은 회원정보를 사용해서 삭제
	@PostMapping(value="/delete")
	public String deletePOST(HttpSession session, MemberVO vo) {
		logger.debug(" /delete -> deletePOST() 호출 ");
		
		// 전달 정보 저장
		logger.debug(" vo : "+vo);
		
		// 서비스 -> DAO 회원정보 삭제
		int result = mService.memberDelete(vo);
		
		// 삭제 여부에 따른 페이지 이동
		if(result == 1) {
			logger.debug(" (๑•̀ㅂ•́)و✧ 회원정보 삭제 완료! ");
			
			// 기존의 사용자 정보(세션) 제거
			session.invalidate();
			
			return "redirect:/member/main";
		}
		
		logger.debug(" ≧ ﹏ ≦ 회원정보 삭제 실패 ");
		return "/member/delete";
		
	}
	
	// 회원목록 조회
	@GetMapping(value="/list")
	public void memberListGET(Model model) {
		logger.debug(" /list -> memberListGET() 호출 ");
		
		// 전달정보 X
		
		// 서비스 -> DAO 회원목록 조회 메서드 호출
		List<MemberVO> ml = mService.memberList();
//		logger.debug(" memberList : "+ml);
		logger.debug(" memberList : "+ml.size());
		
		// DAO에서 가져온 데이터를 뷰페이지에 전달 (Model)
//		model.addAttribute("ml", ml);
		model.addAttribute("ml", mService.memberList());
		
		// 연결된 페이지로 이동
		
	}
	
	
} // Controller
