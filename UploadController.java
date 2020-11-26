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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("board") /* 사용자리스트보기*/
	public String user_list(Model model) { //정보forward위해 model필요함
		model.addAttribute("boardlist",boardDao.getList());
		return "board_list";
	}
	
	@ResponseBody
	@PostMapping("board") /* 정보저장기능 */
	public String content_save(@ModelAttribute BoardVO b) {
		return boardDao.add(b)+""; 
	}	
	
//	@RequestMapping("board/detail") /* 사용자개인정보보기*/
//	@ResponseBody
//	public Map<String,String> showUser(@RequestParam("num") int num) {
//		BoardVO b = boardDao.select(num);
//		Map<String,String> map = new HashMap<>();
//		map.put("id", b.getNum()+"");
//		map.put("author", b.getAuthor());
//		map.put("wdate", b.getWdate());
//		map.put("title", b.getTitle());
//		map.put("contents", b.getContents());
//		return map; // 해당번호의 UserVO객체 return
//	}
	 
	@RequestMapping("upload") // 이용자가 클릭시 upload url을 받아
	public String getForm() {
		return "upload_form"; 
		// post방식으로 form을 보내는 것이 아니라 get으로 버튼클릭시 form 가져옴
	}
	
	@PostMapping("upload")
	@ResponseBody //별도의 jsp없이 바로 브라우저에서 출력
	public String upload(@RequestParam("files")MultipartFile[] mfiles,
			HttpServletRequest request,
			@RequestParam("author") String author) {
		ServletContext context = request.getServletContext();
		String savePath = context.getRealPath("/WEB-INF/upload");
		
		try {
			for(int i=0;i<mfiles.length;i++) {
				mfiles[i].transferTo(
					new File(savePath+"/"+mfiles[i].getOriginalFilename()));
			}
			String msg = String.format("파일(%d)저장성공(작성자:%s)",mfiles.length,author);
			return msg;
		} catch(Exception e) {
			e.printStackTrace();
			return "파일 저장 실패:";
		}
	}
	
	@GetMapping("download/{filename}")
	public ResponseEntity<Resource> download(
			HttpServletRequest request,
			@PathVariable String filename){
		Resource resource = resourceLoader.getResource("WEB-INF/upload/"+filename);
		System.out.println("파일명:"+resource.getFilename());
		String contentType = null;
		try {
			contentType = request.getServletContext()
					.getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(contentType == null) {
			contentType = "application/octet-stream";
		}
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\""+resource.getFilename()+"\"")
				.body(resource);
		}
	}
