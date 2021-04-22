package org.zerock.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.zerock.domain.BoardAttachVO;
import org.zerock.mapper.BoardAttachMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Component
public class FileCheckTask {

	@Setter(onMethod_=@Autowired)
	private BoardAttachMapper attachMapper;
	
	//전 날의 폴더 정보 얻기
	private String getFolderYesterDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
		//하루 전
		cal.add(Calendar.DATE, -1);
		
		//하루 전날을 yyyy-MM-dd 형태의 문자열로 변경
		String str = sdf.format(cal.getTime());
		//yyyy-MM-dd형태의 문자열을 yyyy/MM/dd형태(즉,폴더형태)로 변경
		return str.replace("-", File.separator);
	}
	
	
	@Scheduled(cron = "0 0 2 * * *") //매일 새벽2시에 작업
	public void checkFiles()throws Exception{
		log.warn("File Check Task run........................");
		log.warn(new Date());
		
		//file 리스트 
		List<BoardAttachVO> fileList = attachMapper.getOldFiles();
		
		//db에서 얻은 file리스트로 파일디렉토리 점검준비
		//Stream
		List<Path> fileListPaths = 
		 fileList.stream().map(vo->Paths.get("c:\\upload",
					                                         vo.getUploadPath(),
					                                         vo.getUuid()+"_"+vo.getFileName())).collect(Collectors.toList());
		
		
		//image file - 썸네일 처리
	/*	fileList.stream().filter(new Predicate<BoardAttachVO>() {
			@Override
			public boolean test(BoardAttachVO vo) {
				return vo.isFileType();
			}
		}).map(new Function<BoardAttachVO, Path>() {
			@Override
			public Path apply(BoardAttachVO vo) {
				return Paths.get("c:\\upload", vo.getUploadPath(),"s_"+vo.getUuid()+"_"+vo.getFileName());
			}
		}).collect(Collectors.toList());
		*/
		fileList.stream()
		         .filter(vo-> vo.isFileType())
		         .map(vo->Paths.get("c:\\upload", vo.getUploadPath(),"s_"+vo.getUuid()+"_"+vo.getFileName()))
		         .collect(Collectors.toList());
		
		log.warn("==========================");
		
		fileListPaths.forEach(p->log.warn(p));
		
		//지난파일 저장 디렉토리 
		File targetDir =Paths.get("c:\\upload",getFolderYesterDay()).toFile();
		
		//db에서 삭제되었지만 디렉토리에 남아있는 파일들 얻기
		File[] removeFiles = targetDir.listFiles(file->fileListPaths.contains(file.toPath())==false);
		
		log.warn("--------------------------------------------------");
		for(File file:removeFiles) {
			log.warn(file.getAbsolutePath());
			log.warn(file.getName());
			//파일삭제
			file.delete();
		}
	}
	
	
}
