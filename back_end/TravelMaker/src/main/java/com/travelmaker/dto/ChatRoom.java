package com.travelmaker.dto;

public class ChatRoom {

	private int id;
	private int participation;
	private int travelNumber;

	public ChatRoom() {
		super();
	}

	public ChatRoom(int id, int participation, int travelNumber) {
		super();
		this.id = id;
		this.participation = participation;
		this.travelNumber = travelNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParticipation() {
		return participation;
	}

	public void setParticipation(int participation) {
		this.participation = participation;
	}

	public int getTravelNumber() {
		return travelNumber;
	}

	public void setTravelNumber(int travelNumber) {
		this.travelNumber = travelNumber;
	}

	@Override
	public String toString() {
		return "ChatRoom [id=" + id + ", participation=" + participation + ", travelNumber=" + travelNumber + "]";
	}

}
