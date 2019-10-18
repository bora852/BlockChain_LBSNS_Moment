package com.travelmaker.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.travelmaker.dto.Chat;
import com.travelmaker.dto.User;



@Mapper
public interface ChatMapper {
	/**
	 * 삭제되지 않은 채팅 조회
	 * @return 채팅 메세지
	 */
	public List<Chat> selectAllNotRemovedReview(int rn);
	//로그인
	public List<Chat> selectAll(int rn);
	
	public int insertChat(Chat c);
	
	public int deleteChatById(int id);
	
	public int updateChat(Chat c);
}
