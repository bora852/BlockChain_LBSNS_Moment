package com.travelmaker.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.travelmaker.dto.Chat;
import com.travelmaker.dto.ChatRoom;



@Mapper
public interface ChatRoomMapper {
	/**
	 * 삭제되지 않은 채팅 조회
	 * @return 채팅 메세지
	 */
	public List<ChatRoom> selectAll();
	
	public List<ChatRoom> selectAllByTid(int uid);
	public List<ChatRoom> selectAllByUid(int uid);
	
	public int insertChatRoom(ChatRoom cr);
	public int deleteChatRoom(ChatRoom cr);
}
