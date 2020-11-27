package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.BoardDAO;

@Controller
public class UploadController {
	
	private BoardDAO boardDao;
	
	@Autowired
	ResourceLoader resourceLoader;
	public UploadController(@Qualifier("boardDao") BoardDAO dao) {
		this.boardDao = dao;
	}
	
	@GetMapping("board") /*사용자리스트보기*/
	public String board_list(Model model) { //정보forward위해 model필요함
		model.addAttribute("boardlist",boardDao.getList());
		return "board_list";
	}
	
	@GetMapping("detail") /*개인컨텐츠보기*/
	public String contents(Model model, @RequestParam("num") int num) { //정보forward위해 model필요함
		model.addAttribute("board",boardDao.select(num));
		return "contents";
	}
	
	@RequestMapping("upload") /*upload form 보기*/
	public String getForm() {
		return "upload_form"; 
		// post방식으로 form을 보내는 것이 아니라 get으로 버튼클릭시 form 가져옴
	}
	
	@ResponseBody
	@RequestMapping("detail") /* 정보수정기능 */
	public String user_update(@ModelAttribute BoardVO b) {		
		return boardDao.update(b)+"";
	}
	
	@ResponseBody
	@DeleteMapping("detail") /* 정보삭제기능 */
	public String board_delete(@RequestParam("num") int num) {
		return boardDao.delete(num)+"";
	}
	
	@ResponseBody
	@PostMapping("board") /* 정보저장기능 */
	public String content_save(@ModelAttribute BoardVO b) {
		return boardDao.add(b)+""; 
	}	
}
