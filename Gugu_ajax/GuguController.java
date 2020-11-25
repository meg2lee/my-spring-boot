package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 서버상의 요청과 응답을 담당
public class GuguController {
	
	@GetMapping("ajax_req") /* */
	public String ajax_test() {
		return "ajax_test"; /* ajax_test.jsp로 보냄 */ 
	}
	
	@ResponseBody /* return값이 view가 아닌 HTTP Response Body에 출력 
                     RestController와 동일하게 text 그 자체를 출력 */
	@PostMapping("ajax_req") /* post방식으로 응답받기 */
	public String ajax_req(@RequestParam("dan") int dan) {		
		String msg = "";
		for(int i=1;i<=9;i++) {
			msg += dan + " * " + i + " = " + (dan*i) + "<br>";			
		}
		return msg; // 응답데이터(ajax)
	}

}
