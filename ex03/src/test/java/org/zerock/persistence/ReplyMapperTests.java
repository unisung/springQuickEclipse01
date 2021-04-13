package org.zerock.persistence;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.BoardMapper;
import org.zerock.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	
	//테스트 전에 반드시 게시글의 번호 존재 여부 확인
	private Long[] bnoArr = {97L, 98L, 99L, 100L, 101L};
	
	
	@Setter(onMethod_=@Autowired)
	private ReplyMapper mapper;

	@Ignore
	@Test
	public void testCreate() {
		IntStream.rangeClosed(1,10).forEach(i->{
			ReplyVO vo =new ReplyVO();
		   //게시물 번호
			vo.setBno(bnoArr[i %5]);//bnoArr[1%5]=bnoArr[1], bnoArr[2%5]=bnoArr[2],bnoArr[0%5]=bnoArr[0]
			vo.setReply("댓글 테스트 "+ i);
			vo.setReplyer("replyer" + i);
			
			mapper.insert(vo);
			
		});
	}
	
	@Test
	public void testRead() {
		Long targetRno = 5L;
		ReplyVO vo = mapper.read(targetRno);
		
		log.info(vo);
	}
	
	@Ignore
	@Test
	public void testMapper() {
		log.info(mapper);
	    assertNotNull(mapper);
	}
	
	
}
