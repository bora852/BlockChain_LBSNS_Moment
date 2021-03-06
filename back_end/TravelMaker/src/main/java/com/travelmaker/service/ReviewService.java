package com.travelmaker.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelmaker.dao.IsLikeMapper;
import com.travelmaker.dao.MaxLikeMapper;
import com.travelmaker.dao.ReviewMapper;
import com.travelmaker.dto.Location;
import com.travelmaker.dto.MaxLike;
import com.travelmaker.dto.Review;
import com.travelmaker.dto.ReviewListDTO;
import com.travelmaker.dto.ReviewWithDistance;
import com.travelmaker.dto.ReviewWithDistanceImg;
import com.travelmaker.dto.ReviewWithImage;

@Service
public class ReviewService {

	@Autowired
	private ReviewMapper reviewMapper;

	@Autowired
	private ReviewImageService reviewImgService;

	@Autowired
	private UserService userService;

	@Autowired
	private MaxLikeMapper maxLikeMapper;

	@Autowired
	private IsLikeMapper isLikeMapper;

	/**
	 * 리턴받은 리스트에 사진정보를 추가해 리턴
	 * 
	 * @param location
	 * @return
	 */
	public List<ReviewListDTO> mainReviewList(Location location) {
		List<ReviewListDTO> list = reviewMapper.mainReviewList(location);
		for (ReviewListDTO review : list) {
			String userImageData = userService.getUserImage(review.getImgName());
			String imageData = reviewImgService.getReviewImage(review.getId());
			review.setUserImgData(userImageData);
			review.setImageData(imageData);
		}
		return list;
	}

	public List<ReviewListDTO> userReviewList(String email) {
		List<ReviewListDTO> list = reviewMapper.userReviewList(email);
		for (ReviewListDTO review : list) {
			String userImageData = userService.getUserImage(review.getImgName());
			String imageData = reviewImgService.getReviewImage(review.getId());
			review.setUserImgData(userImageData);
			review.setImageData(imageData);
		}
		return list;
	}

	/**
	 * 위도와 경도로 5km이내의 리뷰 리스트에 사진을 추가해서 리턴
	 * 
	 * @param location (위도, 경도)
	 * @return List<ReviewWithDistance>
	 */
	public List<ReviewWithDistanceImg> reviewListByLocation(Location location) {
		List<ReviewWithDistance> list = reviewMapper.reviewListByLocation(location);
		List<ReviewWithDistanceImg> result = new ArrayList<ReviewWithDistanceImg>();

		for (ReviewWithDistance RWD : list) {
			result.add(new ReviewWithDistanceImg(RWD, reviewImgService.getReviewImage(RWD.getId())));
		}

		return result;
	}

	/**
	 * 전체 리뷰에 사진을 더한 리스트 반환
	 * 
	 * @return List<Review>
	 */
	public List<ReviewWithImage> reviewList() {

		List<Review> list = reviewMapper.reviewList();
		List<ReviewWithImage> result = new ArrayList<>();
		for (Review review : list) {
			result.add(new ReviewWithImage(review, reviewImgService.getReviewImage(review.getId())));
		}
		return result;
	}

	/**
	 * 리뷰 id로 리뷰 리스트 검색후 반환
	 * 
	 * @param id 리뷰 테이블의 id
	 * @return ReviewWithImage
	 */
	public ReviewWithImage selectReviewById(int id) {
		Review review = reviewMapper.selectReviewById(id);
		ReviewWithImage result = new ReviewWithImage(review, reviewImgService.getReviewImage(review.getId()));
		return result;
	}

	/**
	 * 유저 아이디로 리뷰 리스트 검색 후 반환
	 * 
	 * @param uid 유저 아이디
	 * @return List<ReviewWithImage>
	 */
	public List<ReviewWithImage> selectReviewByUid(int uid) {
		List<Review> list = reviewMapper.selectReviewByUid(uid);

		List<ReviewWithImage> result = new ArrayList<>();
		for (Review review : list) {
			result.add(new ReviewWithImage(review, reviewImgService.getReviewImage(review.getId())));
		}
		return result;
	}

	/**
	 * 리뷰 입력, 성공시 1, 실패시 0 반환
	 * 
	 * @param ReviewWithDistance
	 * @return int 1 / 0
	 */
	public int insertReview(Review review) {
		String hasgtag = review.getHashtag();
		StringTokenizer st = new StringTokenizer(hasgtag);
		StringBuilder newHashtag = new StringBuilder();
		while (st.countTokens() != 0) {
			newHashtag.append("#").append(st.nextToken()).append(" ");
		}
		review.setHashtag(newHashtag.toString());
		reviewMapper.insertReview(review);
		maxLikeMapper.insertMaxLike(new MaxLike(0, review.getUid(), review.getId(), 0));
		isLikeMapper.insertPoint(review.getId());
		return review.getId();
	}

	/**
	 * 좋아요 클릭시 호출, 리뷰 아이디 찾아서 +1
	 * 
	 * @param id
	 * @return
	 */
	public int likeIt(int id) {
		return reviewMapper.likeIt(id);
	}

	/**
	 * 싫어요 클릭시 호출, 리뷰 아이디로 검색후 -1
	 * 
	 * @param id
	 * @return
	 */
	public int unLike(int id) {
		return reviewMapper.unLike(id);
	}
}
