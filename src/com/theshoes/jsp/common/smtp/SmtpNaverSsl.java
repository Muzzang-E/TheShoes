package com.theshoes.jsp.common.smtp;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SmtpNaverSsl {
	public static String sendEmailMessage(String receiveEmail, String context) {
		// 사용할 메일 - naver
		String host = "smtp.naver.com";

		// 발신자의 메일 계정 및 비밀번호
		String user = "yujeong_study@naver.com";
		String pwd = "YUJEONG_STUDY";

		// SMTP 서버 설정
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 465); // naver -> 465, ssl 연결 필요
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true"); // ssl 연결 설정
		props.put("mail.smtp.ssl.trust", "smtp.naver.com");

		// 인증 번호 생성기
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < 10; i++) {
			int rIndex = rnd.nextInt(3);
			switch (rIndex) {
			case 0:
				// a-z
				temp.append((char) ((int) (rnd.nextInt(26)) + 97));
				break;
			case 1:
				// A-Z
				temp.append((char) ((int) (rnd.nextInt(26)) + 65));
				break;
			case 2:
				// 0-9
				temp.append((rnd.nextInt(10)));
				break;
			}
		}
		
		// 생성된 인증번호 String으로 변환
		String AuthenticationKey = temp.toString();
		System.out.println("인증번호 " + AuthenticationKey);

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, pwd);
			}
		});

		MimeMessage msg = new MimeMessage(session);
		try {

			// 발신자 설정
			InternetAddress from = new InternetAddress(user);
			msg.setFrom(from);

			// 수신자 설정
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receiveEmail));
			
			if(context == null) {
				// 메일 제목
				msg.setSubject("[The Shoes] 비밀번호 인증 메일입니다.", "UTF-8");
				// 메일 내용
				msg.setText("인증 번호는 " + AuthenticationKey + " 입니다", "UTF-8");
			} else {
				// 메일 제목
				msg.setSubject("[The Shoes] 아이디 찾기 이메일입니다..", "UTF-8");
				// 메일 내용
				msg.setText("회원님의 아이디는 " + context + " 입니다", "UTF-8");
			}
			
			Transport.send(msg);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return AuthenticationKey;
	}
}