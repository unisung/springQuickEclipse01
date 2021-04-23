package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.BoardMapper;
import org.zerock.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReplyServiceImpl implements ReplyService{
	
	//스프링 버전이 4.3이상이면 @Setter나 @Autowired 생략 가능
	@Setter(onMethod_=@Autowired) 
	private ReplyMapper mapper;
	
	@Setter(onMethod_=@Autowired)
	private BoardMapper boardMapper;//댓글 갯수 변경용 

	@Override
	public int register(ReplyVO vo) {
		log.info("reigster....." + vo);
		
		boardMapper.updateReplyCnt(vo.getBno(), 1);//댓글 갯수 1 증가
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		log.info("get....." + rno);
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		log.info("modify....." + vo);
		return mapper.update(vo);
	}

	@Override
	public int remove(Long rno) {
		log.info("remove....." + rno);
		
	    ReplyVO vo = mapper.read(rno);
	    
		boardMapper.updateReplyCnt(vo.getBno(), -1); //댓글 삭제시 댓글 갯수 -1
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		log.info("get Reply List of a Board .." + bno);
		return mapper.getListWithPaging(cri, bno);
	}

	/* 댓글 갯수와 댓글 리스트 추출 메소드 */
	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long bno) {
		return new ReplyPageDTO(mapper.getCountByBno(bno),
				                               mapper.getListWithPaging(cri, bno));
	}

}
