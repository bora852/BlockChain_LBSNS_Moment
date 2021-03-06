package com.travelmaker.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Base64;
import java.util.Base64.Decoder;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travelmaker.dao.UserMapper;
import com.travelmaker.dto.User;
import com.travelmaker.dto.UserImage;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private EmailService mailsender;

	// 회원 전체 조회
	public List<User> selectAllUser() {
		return userMapper.selectAllUser();
	}

	// 정상 회원 전체 조회
	public List<User> selectNotRemovedAllUser() {
		return userMapper.selectNotRemovedAllUser();
	}

	// 로그인
	public User loginUser(User user) {
		return userMapper.loginUser(user);
	}

	// 회원조회 - id
	public User selectOneUser(int id) {
		return userMapper.selectOneUser(id);
	}

	// 회원조회 - 이메일
	public User selectOneUserByEmail(String email) {
		return userMapper.selectOneUserByEmail(email);
	}

	// 회원가입 & 인증메일
	public int insertUser(User user) {
		try {
			// 회원가입
			userMapper.insertUser(user);
			// 인증 메일 보내기
			return mailsender.mailSendWithUserKey(user);
		} catch (DuplicateKeyException e) {
			System.out.println("Duplicate error : " + e);
			return 0;
		}
	}

	// 회원수정/회원삭제
	public int updateUser(User user) {
		return userMapper.updateUser(user);
	}

	// 회원탈퇴(보류)
	public int deleteUser(int id) {
		return userMapper.deleteUser(id);
	}

	// 아이디 찾기
	public User findId(User user) {
		return userMapper.findId(user);
	}

	// 비밀번호 찾기
	public User findPass(User user) {
		return userMapper.findPass(user);
	}

	// 회원 이미지 등록
	public int uploadImage(UserImage image) {

		int result = 0;
		String imgData = image.getImgData();
		String imgName = image.getImgName();
		Decoder decoder = Base64.getDecoder();
		byte[] decoderByte = decoder.decode(imgData.split(",")[1]);
		try {
			FileOutputStream fos = new FileOutputStream("image/" + imgName);
			fos.write(decoderByte);
			fos.close();
			result = userMapper.insertUserImage(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 회원 이미지 수정 전 기존 이미지 삭제
	public int deleteUploadImage(String email) {
		int result = 0;
		int cnt = userMapper.selectCountUserImage(email);
		if (cnt > 0) {
			userMapper.deleteUserImage(email);
			result = 1;
		}
		return result;
	}

	// 회원 이미지 조회
	public List<UserImage> selectUserImage(String email) {
		List<UserImage> userImage = userMapper.selectUserImage(email);

		for (UserImage img : userImage) {

			try {
				File f = new File("image/" + img.getImgName());
				FileInputStream fis;
				try {
					fis = new FileInputStream(f);
				} catch (FileNotFoundException e) {
					f = new File("image/tempImg.jpg");
					fis = new FileInputStream(f);
				}
				byte byteArray[] = new byte[(int) f.length()];
				fis.read(byteArray);
				String encodeImg = "data:image/" + img.getImgName() + ";base64, "
						+ Base64.getEncoder().encodeToString(byteArray);

				img.setImgData(encodeImg);
				fis.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return userImage;
	}

	public void updateCert(User user) {
		userMapper.updateCert(user);
	}
	
	/**
	 * 파일 이름으로 유저 프로필을 찾아 스트링 데이터로 리턴해주는 함수
	 * 
	 * @param imgName
	 * @return
	 */
	public String getUserImage(String imgName) {
		String result = null;

		try {
			File f = new File("image/" + imgName);
			FileInputStream fis;
			try {
				fis = new FileInputStream(f);
			} catch (FileNotFoundException e) {
				f = new File("image/tempImg.jpg");
				fis = new FileInputStream(f);
			}
			byte byteArray[] = new byte[(int) f.length()];
			fis.read(byteArray);
			String encodeImg = "data:image/" + imgName + ";base64, " + Base64.getEncoder().encodeToString(byteArray);

			result = encodeImg;
			fis.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
