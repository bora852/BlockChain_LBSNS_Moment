package com.travelmaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelmaker.dao.ChatRoomMapper;
import com.travelmaker.dto.ChatRoom;

@Service
public class ChatRoomService {

	@Autowired
	private ChatRoomMapper chatRoomMapper;

	public List<ChatRoom> selectAll() {
		return chatRoomMapper.selectAll();
	}

	public List<ChatRoom> selectAllByTid(int tid){
		return chatRoomMapper.selectAllByTid(tid);
	}
	public List<ChatRoom> selectAllByUid(int uid){
		return chatRoomMapper.selectAllByUid(uid);
	}
	
	public int insertChatRoom(ChatRoom cr) {
		return chatRoomMapper.insertChatRoom(cr);
	}
	public int deleteChatRoom(ChatRoom cr) {
		return chatRoomMapper.deleteChatRoom(cr);
	}

}
