package org.zerock.persistence;

import java.util.List;
import java.util.function.Consumer;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	@Setter(onMethod_=@Autowired)
	private BoardMapper boardMapper;

	@Ignore
	@Test
	public void testGetTime() {
		log.info(boardMapper.getClass().getName());//클래스명 출력
		boardMapper.getList().forEach(board->log.info(board));//클래스명 출력
	}
	
	@Ignore
	@Test
	public void insertBoard() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newbie");
		
		boardMapper.insert(board);
		
		log.info(board);
	}
	
	@Ignore
	@Test
	public void insertSelectKeyBoard() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글 key");
		board.setContent("새로 작성하는 내용 key");
		board.setWriter("newbie");
		
		boardMapper.insertSelectKey(board);
		
		log.info(board);
	}
	
	@Ignore
	@Test
	public void testUpdate() {
		BoardVO board =  boardMapper.read(5L);
		//board.setBno(5L);
		board.setTitle("수정 타이틀");
		board.setContent("수정 컨텐츠");
		
		boardMapper.update(board);
	}
	
	@Ignore
	@Test
	public void testRead() {
		BoardVO board = boardMapper.read(5L);
		
		log.info(board);
		
	}
	
	@Ignore
	@Test
	public void testDelete() {
		boardMapper.delete(1L);
	}
	
	
	@Test
	public void testPaging() {
		Criteria cri = new Criteria();
		//10개씩 3페이지
		cri.setPageNum(3);//3페이지
		cri.setAmount(10);//페이지당 10개 글
		
		List<BoardVO> list = boardMapper.getListWithPaging(cri);
		
		list.forEach(board-> log.info(board.getBno()));
	}
	
	
}
