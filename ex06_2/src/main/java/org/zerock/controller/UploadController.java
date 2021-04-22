package org.zerock.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.crypto.URIDereferencer;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
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
		
		//업로드 파일 경로 얻기
		String uploadFolderPath = getFolder();//년\월\일  디렉토리
		
		// 폴더 생성하기.....
		File uploadPath = new File(uploadFolder, uploadFolderPath);// c:\\upload\\년\\월\\일\\파일명
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
			     attachDTO.setUploadPath(uploadFolderPath);//upload경로
			     
			  
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
	
	
	// 썸네일 보여주기 
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName){
		log.info("fileName: " + fileName);
		
		File file = new File("c:\\upload\\"+fileName);
		
		log.info("file: " + file);
		
		ResponseEntity<byte[]> result =null;
		
		try {
			
			    HttpHeaders header = new HttpHeaders();
			    
			    header.add("Content-Type", Files.probeContentType(file.toPath()));
			    result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),header, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//파일 다운로드
	@GetMapping(value="/download",
			              produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent, String fileName){
		
		log.info("download file: " + fileName);
		
		Resource resource = new FileSystemResource("c:\\upload\\" + fileName);
		
		//해당하는 파일이 존재하지 않으면 NOT_FOUND 리턴
		if(!resource.exists()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		
		log.info("resource:" + resource);
		
		String resourceName = resource.getFilename();
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
		        String downloadName =null;
		        //브라우저 확인(IE여부 확인)
		        if(userAgent.contains("Trident")) {
		        	log.info("IE browser");
		        	downloadName=URLEncoder.encode(resourceName, "UTF-8").replaceAll("\\+"," ");
		        	
		        }else if(userAgent.contains("Edge")) {
		        	log.info("Edge browser");
		        	downloadName=URLEncoder.encode(resourceName, "UTF-8");
		        }else {
		        	log.info("Chrome browser");
		        	downloadName=new String(resourceName.getBytes("UTF-8"), "ISO-8859-1");
		        }
		        
		        log.info("downloadName:"+downloadName);
		        
		     
			//headers.add("Content-Disposition","attachment; filename="+new String(resourceName.getBytes("UTF-8"),"ISO-8859-1"));
		    headers.add("Content-Disposition","attachment; filename="+downloadName);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	//upload파일 삭제 
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String type){
		log.info("deleteFile: " + fileName);
		
		File file;
		
		try { 
			     //upload디렉토리에서 해당파일 정보 얻기
			      file = new File("c:\\upload\\"+URLDecoder.decode(fileName,"UTF-8"));
			      //파일 삭제
			      file.delete();
			      
			      //파일타입이 iamge이면 썸네일 파일도 삭제
			      if(type.equals("image")) {
			    	  String largeFileName = file.getAbsolutePath().replace("s_","");
			    	  log.info("largeFileName: "+largeFileName);
			    	  file = new File(largeFileName);
			    	  file.delete();
			      }
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("deleted",HttpStatus.OK);
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
