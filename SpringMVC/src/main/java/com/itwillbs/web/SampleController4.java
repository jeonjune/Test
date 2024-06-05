package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SampleController4 {

	private static final Logger logger = LoggerFactory.getLogger(SampleController4.class);
	
	// http://localhost:8088/web/doD
	// http://localhost:8088/web/doD?msg=itwill
	@RequestMapping(value="/doD", method=RequestMethod.GET)
	public String doD(RedirectAttributes rttr /* @ModelAttribute("msg") String msg */) {
		logger.debug(" /doD -> doD()호출");
		
		// RedirectAttributes : 특별한 Model 객체(쿠팡맨), 반드시 redirect 이동시에만 사용가능 
		
		rttr.addFlashAttribute("msg", "addFlashAttribute 정보입니다");
		// => 데이터를 일회성으로 전달(새로고침하면 정보는 사라짐)
		
//		rttr.addAttribute();
		// => 정보가 주소줄에 표시O, F5(새로고침) 데이터 유지
//		rttr.addFlashAttribute();
		// => 정보가 주소줄에 표시X, F5(새로고침) 데이터 사라짐
		
//		return "/doE"; // 단순 뷰페이지 설정
//		return "redirect:/doE"; // 주소 바뀌고 페이지도 바뀜
//		return "forward:/doE";  // 주소 바뀌지 않고 페이지 바뀜
		
		return "redirect:/doE"; 
	}
	
	// http://localhost:8088/web/doE
	// http://localhost:8088/web/doE?msg=itwill
	@RequestMapping(value="/doE", method=RequestMethod.GET)
	public void doE(@ModelAttribute("msg") String msg) {
		logger.debug(" /doE -> doE()호출");
		
	}
}
