package com.ssafy.triends.domain.user.util;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Properties;

public class MailUtil {
	private static final String bodyEncoding = "UTF-8";
	private static MailUtil instance = null;
	
	private MailUtil() {}
	
	public static MailUtil getInstance() {
		if (instance == null)
			instance = new MailUtil();
		return instance;
	}
	
	public static String getRandomPassword(int size) {
        char[] charSet = new char[] {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '!', '@', '#', '$', '%', '^', '&' };

        StringBuffer sb = new StringBuffer();
        SecureRandom sr = new SecureRandom();
        sr.setSeed(new Date().getTime());

        int idx = 0;
        int len = charSet.length;
        for (int i=0; i<size; i++) {
            // idx = (int) (len * Math.random());
            idx = sr.nextInt(len);    // 강력한 난수를 발생시키기 위해 SecureRandom을 사용한다.
            sb.append(charSet[idx]);
        }

        return sb.toString();
    }
	
	public static String sendMail(String userEmail) throws Exception{
	    String subject = "[EnjoyTrip] 비밀번호 재설정 안내";
	    String newPwd = getRandomPassword(8);
	    StringBuilder contentSb = new StringBuilder("");
	    contentSb.append("<div style=\"margin: 15px\">\r\n").append("<div style=\"background-color: rgba(0, 0, 0, 0.05); padding: 5px 20px\">\r\n").append("<h1 style=\"color: #118c8c; display: inline-block; margin-right: 25px\">\r\n");
	    contentSb.append("<span>\r\n").append("EnjoyTrip\r\n").append("</span>\r\n").append("</h1>\r\n").append("비밀번호 재설정 안내\r\n").append("</div>\r\n");
	    contentSb.append("\r\n").append("<div>\r\n<br/><br/>").append("<p>안녕하세요, 고객님.<br />즐거운 여행을 도와드리는 <b>EnjoyTrip</b>입니다.</p>\r\n").append("<br />요청하신 비밀번호 재설정 방법을 안내드립니다.<br />아래에 나온 임시 비밀번호를 입력해\r\n").append("로그인하신 후, 마이페이지에서 비밀번호를 변경해주세요. <br /><br />감사합니다.\r\n"); 
	    contentSb.append("</div>\r\n").append("\r\n").append("<div\r\n").append("style=\"\r\n").append("display: inline-block;\r\n").append("width: 400px;\r\n").append("background-color: rgba(0, 0, 0, 0.05);\r\n").append("padding: 35px 35px;\r\n"); 
	    contentSb.append("margin-top: 50px;\r\n").append("text-align: center;\r\n").append("font-size: larger;\r\n").append("\"\r\n").append(">\r\n"); 
	    contentSb.append("임시 비밀번호 : <b>").append(newPwd).append("</b>\r\n").append("</div>\r\n").append("</div>");
	    //contentSb.append(newPwd);
	    String content = contentSb.toString();
	    //수신인
	    String[] recipients = { userEmail };
	    
	    //메일 옵션 설정
	    Properties props = new Properties();    
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "465");
	    props.put("mail.smtp.auth", "true");
	 
	    props.put("mail.smtp.quitwait", "false");
	    props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.socketFactory.fallback", "false");
	    
	    try {
	      //메일 서버  인증 계정 설정
	      Authenticator auth = new Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	          return new PasswordAuthentication("email", "password");
	        }
	      };
	      
	      
	      //메일 세션 생성
	      Session session = Session.getInstance(props, auth);
	      
	      //MIME 타입 설정
	      MailcapCommandMap MailcapCmdMap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
	      MailcapCmdMap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
	      MailcapCmdMap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
	      MailcapCmdMap.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
	      MailcapCmdMap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
	      MailcapCmdMap.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
	      CommandMap.setDefaultCommandMap(MailcapCmdMap);
	      
	      //메일 송/수신 옵션 설정
	      Message message = new MimeMessage(session);
	      message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
	      message.setFrom(new InternetAddress("dev.cheww.server@gmail.com", "EnjoyTrip", "utf-8"));
	      message.setSubject(subject);
	      message.setSentDate(new Date());
		  message.setContent(content, "text/html; charset=utf-8");

		  Transport.send(message);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return newPwd;
	}
}
