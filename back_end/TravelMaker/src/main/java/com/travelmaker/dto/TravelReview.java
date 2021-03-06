package com.travelmaker.dto;

import com.travelmaker.dto.enumeration.Status;

public class TravelReview {

	private int id;
	private int tid;
	private String title;
	private String hashtag;
	private String review;
	private Status isRemove;

	public TravelReview() {
		super();
	}
	
	public TravelReview(int id, int tid, String title, String hashtag, String review, Status isRemove) {
		super();
		this.id = id;
		this.tid = tid;
		this.title = title;
		this.hashtag = hashtag;
		this.review = review;
		this.isRemove = isRemove;
	}

	public Status getIsRemove() {
		return isRemove;
	}

	public void setIsRemove(Status isRemove) {
		this.isRemove = isRemove;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return "TravelReview [id=" + id + ", tid=" + tid + ", title=" + title + ", hashtag=" + hashtag + ", review="
				+ review + ", isRemove=" + isRemove + "]";
	}

	

}
