package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // 서버상의 요청과 응답을 담당
public class GuguController {
	
	 /* get방식으로 요청시 */
	@GetMapping("/gugu/{dan}")/* dan이라는 parameter를 받아 gugu함수를 실행 */
	public String gg(@PathVariable("dan") int dan, Model model) {
	/*시스템이 호출(main함수x)
	 url path(/)안에 들어있는 변수를 받아 int로 변환하여 저장(문자열로 받지만 정수로 자동변환) */
		String msg = "";
		for(int i=1;i<=9;i++) {
			msg += dan + " * " + i + " = " + (dan*i) + "<br>";			
		}
		model.addAttribute("data",msg); 
		/* data에 msg값을 담아 jsp에 forward(.setAttribute 기능 + forward기능 포함)
		   mvc패턴에서 model은 data를 담당하기 때문에 데이터전달은 model사용*/
		return "gugu"; /* view 파일로 응답 */
	}
	
	@GetMapping("/gugu/param")
	public String gugu(@RequestParam("dan") int dan, Model model) {
		/* Model은 request.getParameter와 같은 기능을 하는 annotation으로서 영역객체에 data를 공유하기위해 要 */
		String msg = "";
		for(int i=1;i<=9;i++) {
			msg += dan + " * " + i + " = " + (dan*i) + "<br>";			
		}
		model.addAttribute("data",msg); 
		return "gugu"; /* view 파일로 응답 */
	}

}
