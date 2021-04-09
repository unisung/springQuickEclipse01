package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
	//DI
	//spring 4.3부터 자동 처리(@Autowired)
	//@Setter(onMethod_=@Autowired)
	private BoardMapper mapper;

	@Override
	public void register(BoardVO board) {
		mapper.insert(board);
		//mapper.insertSelectKey(board);	
	}

	@Override
	public BoardVO get(Long bno) {
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		 mapper.update(board) ;
		 return true;
	}

	@Override
	public boolean remove(Long bno) {
		mapper.delete(bno);
		return true;
	}

	@Override
	public List<BoardVO> getList() {
		return mapper.getList();
	}

	

}
