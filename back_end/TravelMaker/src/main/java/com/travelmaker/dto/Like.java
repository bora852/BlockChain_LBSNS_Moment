package com.travelmaker.dto;

public class Like {

	private int id;
	private int uid;
	private int rid;

	public Like(int id, int uid, int rid) {
		super();
		this.id = id;
		this.uid = uid;
		this.rid = rid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	@Override
	public String toString() {
		return "Like [id=" + id + ", uid=" + uid + ", rid=" + rid + "]";
	}

}
