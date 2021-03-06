package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyMapper {
	public int insert(ReplyVO vo);
   
	public ReplyVO read(Long rno);//답변글 번호로 답변 구하기
	
	public int delete(Long rno);//댓글 번호로 댓글 삭제
	
	public int update(ReplyVO reply);
	
	public List<ReplyVO> getListWithPaging(@Param("cri") Criteria cri, 
			                                                 @Param("bno") Long bno);
	//댓글 갯수 계산 메소드 추가
	public int getCountByBno(Long bno);
	
}
