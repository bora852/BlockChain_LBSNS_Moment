package com.travelmaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelmaker.dao.TravelReviewMapper;
import com.travelmaker.dao.AccompanyRegistMapper;
import com.travelmaker.dto.TravelReview;
import com.travelmaker.dto.User;

@Service
public class TravelReviewService {
	
	@Autowired
	private TravelReviewMapper travelReviewMapper;

	public List<TravelReview> selectAllReview(){
		return travelReviewMapper.selectAllReview();
	}
	
	public List<TravelReview> selectNotRemovedReview(){
		return travelReviewMapper.selectAllNotRemovedReview();
	}
	
	public List<TravelReview> selectAllReviewByTid(int tid){
		return travelReviewMapper.selectAllReviewByTid(tid);
	}
	
	public int updateReview(TravelReview tr) {
		return travelReviewMapper.updateReview(tr);
	}
	
	public int deleteReviewById(int id) {
		return travelReviewMapper.deleteReviewById(id);
	}

	public int insertReview(TravelReview tr) {
		return travelReviewMapper.insertReview(tr);
	}
	
	public TravelReview selectReview(int id) {
		return travelReviewMapper.selectReview(id);
	}
}
