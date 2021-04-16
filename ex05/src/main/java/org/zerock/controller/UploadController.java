package org.zerock.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.AttachFileDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

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
	
	//@PostMapping("/uploadAjaxAction")
	@PostMapping(value="/uploadAjaxAction",
			             produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	//public void uploadAjaxAction(MultipartFile[] uploadFile) {
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxAction(MultipartFile[] uploadFile) {
		//전송완료된 파일 목록 저장 리스트
		List<AttachFileDTO> list = new ArrayList<AttachFileDTO>();
		
		log.info("upload ajax post.........");
		log.info("upload ajax post...업로드 컨트롤러 ......");
		
		//파일시스템의 업로드 경로
		String uploadFolder = "c:\\upload";
		
		// 폴더 생성하기.....
		File uploadPath = new File(uploadFolder, getFolder());// c:\\upload\\년\\월\\일\\파일명
		log.info("upload path: " + uploadPath);
		
		//파일경로상의 디렉토리가 없으면 디렉토리 생성하기
		if(!uploadPath.exists()) {
			uploadPath.mkdirs();
		}// make yyyy/MM/dd folder
		
		
		log.info("파일: "+uploadFile.length);
		
		for(MultipartFile multipartFile : uploadFile) {
			//업로드된 파일 정보 저장 객체 생성
			AttachFileDTO attachDTO = new AttachFileDTO();
			
			log.info("-----------------------------------");
			log.info("upload File Name: " + multipartFile.getOriginalFilename() );
			log.info("upload File Size: " + multipartFile.getSize() );
			
			//전송할 파일명 얻기
		  String uploadFileName = multipartFile.getOriginalFilename();
		  
		  //IE has file path
		  uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
		  log.info("only file name: " + uploadFileName);
		  
		  //전송될 파일 명 얻기
		  attachDTO.setFileName(uploadFileName);
		  
		  /* 전송되는 파일명이 동일하지 않도록 UUID를 이용한 랜덤 파일명 생성 처리 */
		  UUID uuid = UUID.randomUUID();
		  //유일한 파일명으로 생성처리 
		  uploadFileName = uuid.toString() + "_" + uploadFileName;
		  
		  //upload할 디렉토리와 파일명으로 전송경로 정보 생성
		 // File saveFile = new File(uploadFolder, uploadFileName);
		  File saveFile = new File(uploadPath, uploadFileName);
		  
		  try {
			  //전송처리 transferTo();
			     multipartFile.transferTo(saveFile);
			     //전송후 전송된 파일 정보 저장
			     attachDTO.setUuid(uuid.toString());//uuid
			     attachDTO.setUploadPath(uploadFolder);//upload경로
			     
			  
			     //썸네일 처리 
			     if(checkImageType(saveFile)) {//이미지 파일이면 썸네일 처리
			    	 //이미지 파일 이므로 true로 설정
			    	 attachDTO.setImage(true);
			    	 
			    	 //썸네일 파일 정보 생성
			    	 FileOutputStream thumbnail 
			    	              = new FileOutputStream(new File(uploadPath, "s_"+uploadFileName));
                     //썸네일 파일생성
			    	 Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail,100,100);
			    	 //자원 해제
			    	 thumbnail.close();
			     }
			     
			     /********  list에 전송한 파일 정보 add하기   ****** */
			     list.add(attachDTO);
			     
		  }catch(Exception e) {
			  log.error(e.getMessage());
		  }
		}
		// 업로드된 파일 목록정보와 상태코드를 결과 값으로 넘김 - json타입
		return new ResponseEntity<List<AttachFileDTO>>(list, HttpStatus.OK);
	}
	
	//폴더 처리 메소드 
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//
		Date date = new Date();//현재시각 날짜정보
		String str = sdf.format(date);
		return str.replace("-", File.separator);// 년-월-일-파일명 ->년/월/일/파일
	}
	
	//이미지타입여부 체크 메소드 
	private boolean checkImageType(File file) {
		try {
			   // 파라미터로 전달된 파일의 컨텐츠 타입 얻기
			    String  contentType = Files.probeContentType(file.toPath());
			    //컨텐츠 타입이 image로 시작하는지 여부 판단.
			    return contentType.startsWith("image");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
