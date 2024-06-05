package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberVO;

@Controller
public class SampleController3 {

	private static final Logger logger = LoggerFactory.getLogger(SampleController3.class);
	
	// http://localhost:8088/web/doC
	// http://localhost:8088/web/doC?userid=admin&userpw=1234&username=관리자...
	// http://localhost:8088/web/doC?itwill=부산&tel=0518030909
	@RequestMapping(value = "/doC", method = RequestMethod.GET )
	public String doC(/* @ModelAttribute("vo") */ MemberVO vo) {
		// 객체를 선언한 경우, 객체에 저장되는 파라메터 모두를 자동수집 가능
		// 객체에 저장이 불가능한 정보는 자동수집 불가능
		logger.debug(" /doC -> doC() 호출 ");
		logger.debug(" vo : "+vo);
		return "itwill";
	}
	
	// http://localhost:8088/web/doC1
	@RequestMapping(value = "/doC1", method = RequestMethod.GET )
	public String doC1(Model model/* @ModelAttribute("DBVO") MemberVO vo */) {
		logger.debug(" /doC1 -> doC1() 호출 ");
		
		// Model 객체
		// => 스프링에서 제공하는 객체(상자, 컨테이너)
		//    컨트롤러의 정보를 뷰페이지로 전달하는 객체
		
		
		// 객체 생성 ( DAO 에서 생성된 객체 ) => @ModelAttribute 정보전달 X
		MemberVO DBVO = new MemberVO();
		DBVO.setUserid("db admin");
		DBVO.setUserpw("1234");
		DBVO.setUsername("db 이름");
		DBVO.setUseremail("admin@naver.com");
		
		// model 객체에 전달할 정보를 저장
//		model.addAttribute("이름", 값);
//		model.addAttribute("DBVO", DBVO);
		model.addAttribute(DBVO);
		// => 이름의 정보가 존재하지 않는 경우
		//    이름을 강제로 설정(memberVO),
		//    전달하는 객체의 클래스명에서
		//    첫 글자만 소문자로 변경 후 이름을 강제로 설정(memberVO)
		
		
		
		return "itwill";
	}
	
	// http://localhost:8088/web/doC2?userid=admin&userpw=1234&username=관리자...
	@RequestMapping(value = "/doC2", method = RequestMethod.GET )
	public String doC2(MemberVO vo, Model model) {
		logger.debug(" /doC2 -> doC2() 호출 ");		
		
		MemberVO DBVO = new MemberVO();
		DBVO.setUserid("db admin");
		DBVO.setUserpw("1234");
		DBVO.setUsername("db 이름");
		DBVO.setUseremail("admin@naver.com");
		
		model.addAttribute(DBVO);
		
		return "itwill";
	}
	
	
}
