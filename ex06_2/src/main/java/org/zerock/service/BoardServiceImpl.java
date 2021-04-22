package org.zerock.service;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.BoardAttachVO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardAttachMapper;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
	//DI
	//spring 4.3부터 자동 처리(@Autowired)
	//@Setter(onMethod_=@Autowired)
	private BoardMapper mapper;
	
	@Setter(onMethod_=@Autowired)
    private BoardAttachMapper attachMapper;
	
	@Transactional
	@Override
	public void register(BoardVO board) {
		//mapper.insert(board);
		log.info("register....." + board);
		
		//글 등록 후 등록한 글 번호 받기 위한 메소드
		mapper.insertSelectKey(board);
		//첨부파일이 없으면 return;
		if(board.getAttachList()==null || board.getAttachList().size()<=0) {
			return;
		}
		//첨부파일 갯수만큼 첨부파일 저장 처리
		//board.getAttachList().forEach(new Consumer<BoardAttachVO>() {
		//	@Override
		//	public void accept(BoardAttachVO attach) {
		//		attach.setBno(board.getBno());
		//		attachMapper.insert(attach);
		//	}
		//});
		board.getAttachList().forEach(attach-> {
				attach.setBno(board.getBno());
				attachMapper.insert(attach);
		});
	}

	@Override
	public BoardVO get(Long bno) {
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("modify........." + board);
		//게시글 수정시 기존파일을 db에서 삭제 후
		attachMapper.deleteAll(board.getBno());
		
		boolean modifyResult = mapper.update(board)==1;
		
		//새로운 첨부파일 정보룰 db에 저장
		if(modifyResult && (board.getAttachList() != null  && board.getAttachList().size()>0)) {
			board.getAttachList().forEach(attach->{
				 attach.setBno(board.getBno());
				 attachMapper.insert(attach);
			});
		}
		
		 return modifyResult;
	}

	@Transactional
	@Override
	public boolean remove(Long bno) {
		log.info("remove...." + bno);
		//bno에 해당하는 첨부파일 삭제
		attachMapper.deleteAll(bno);
		//글번호 bno에 해당하는 글 삭제
		return mapper.delete(bno)==1;
	}

	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("get List with criteria: " + cri);
		
		return  mapper.getListWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		return mapper.getTotalCount(cri);
	}

	@Override
	public List<BoardAttachVO> getAttachList(Long bno) {
		log.info("get Attach list by bno " + bno);
		
		return attachMapper.findByBno(bno);
	}

//	@Override
//	public List<BoardVO> getList() {
//		return mapper.getList();
//	}

	

}
