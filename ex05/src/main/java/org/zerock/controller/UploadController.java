package org.zerock.controller;

import java.io.File;

import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class UploadController {
	
	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("upload form");
	}
	
	
	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
		//파일 업로드 경로
		String uploadFolder = "c:\\upload";
		
		for(MultipartFile multipartFile :uploadFile ) {
				log.info("-----------------------------------");
				log.info("upload File Name: " + multipartFile.getOriginalFilename() );
				log.info("upload File Size: " + multipartFile.getSize() );
				
				//파일전송 처리
				File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
				
				try {
					  multipartFile.transferTo(saveFile);//파일전송
				}catch(Exception e) {
					log.error(e.getMessage());
				}
		}
	}
	
	@GetMapping("/uploadAjax")
	public void uplaodAjax() {
		log.info("upload ajax");
	}
	
	
}
