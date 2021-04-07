package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	@Select("select sysdate from dual")//annotation방식
    public String getTime();
	
	public String getTime2();//xml과 연동
}
