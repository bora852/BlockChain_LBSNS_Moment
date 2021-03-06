package com.travelmaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.travelmaker.dto.Chat;
import com.travelmaker.dto.ChatRoom;
import com.travelmaker.service.ChatRoomService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class ChatRoomController {
	
	@Autowired
	private ChatRoomService chatRoomService;

	@RequestMapping(value = "/chatRoomAdmin", method = RequestMethod.GET)
	@ApiOperation(value="모든 채팅방  리턴")
	public List<ChatRoom> selectAllChatRoom() throws Exception {
		return chatRoomService.selectAll();
	}
	
	@RequestMapping(value = "/chatRoomTid", method = RequestMethod.POST)
	@ApiOperation(value="tid에 관련된 채팅방  리턴")
	public List<ChatRoom> selectAllChatRoomByTid(@RequestBody int tid) throws Exception {
		return chatRoomService.selectAllByTid(tid);
	}
	
	@RequestMapping(value = "/chatRoomUid", method = RequestMethod.POST)
	@ApiOperation(value="uid에 관련된 채팅방  리턴")
	public List<ChatRoom> selectAllChatRoomByUid(@RequestBody int uid) throws Exception {
		return chatRoomService.selectAllByUid(uid);
	}
	@RequestMapping(value = "/chatRoom", method = RequestMethod.POST)
	@ApiOperation(value="채팅방 생성")
	public int insertChatRoom(@RequestBody ChatRoom cr) throws Exception {
		return chatRoomService.insertChatRoom(cr);
	}
	@RequestMapping(value = "/chatRoom", method = RequestMethod.DELETE)
	@ApiOperation(value="채팅방 삭제")
	public int deleteChatRoom(@RequestBody ChatRoom cr) throws Exception {
		return chatRoomService.deleteChatRoom(cr);
	}
}
