package com.springbook.biz.user;

public interface UserService {
   //CRUD 기능 메소드 구현
	//회원등록
	public void insertUser(UserVO vo);
	
	//회원 조회
	public UserVO getUser(UserVO vo);
	
	//회원 삭제
    public void deleteUser(UserVO vo);
    
  //회원 수정
    public void updateUser(UserVO vo);
}
