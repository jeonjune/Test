package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SampleController2 {

	
	private static final Logger logger = LoggerFactory.getLogger(SampleController2.class);
	
	// http://localhost:8088/web/doB
	// * 메서드 리턴값이 String 타입일때
	//   -> /WEB-INF/views/문자열.jsp 페이지를 연결
	@RequestMapping(value = "/doB", method = RequestMethod.GET)
	public /* int 기본형타입 사용불가 */ String doB() {
		logger.debug(" /doB -> doB() 호출 ");
		logger.debug(" doB() 실행 완료 후 /WEB-INF/views/itwill.jsp 페이지로 이동 ");
		
		return "itwill";
	}
	
	// http://localhost:8088/web/doB1
	// http://localhost:8088/web/doB1?msg=hello
	// http://localhost:8088/web/doB1?msg=hello&age=100
	@RequestMapping(value = "/doB1", method = RequestMethod.GET)
	public String doB1(@ModelAttribute("msg") String value,
					   @ModelAttribute("age") int iValue) {
					// @ModelAttribute("파라메터명") String value
					// -> 주소줄에 전달되는 데이터(파라메터)를 변수 사용해서 저장
		logger.debug(" /doB1 -> doB1() 호출 ");
		logger.debug(" doB1() 실행 완료 후 /WEB-INF/views/itwill.jsp 페이지로 이동 ");
		
		logger.debug(" (❁´◡`❁) msg : "+value); // 파라메터 데이터를 저장
		logger.debug(" (*/ω＼*) age : "+iValue); // 파라메터 데이터를 저장
		// request.setAttribute(속성,값);
		
		return "itwill";
	}
	
}
