package com.gmail.mailTester;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class DemoController {

	@Autowired
	JavaMailSender sender;

	@GetMapping("/home")
	public String home() {
		return "test";
	}

	
@GetMapping("/sendEmail")
public String getAllTender() {
	
	Random ran = new Random();
	int rand = ran.nextInt(999);
	System.out.println("Send mail"+rand);

	SimpleMailMessage msg = new SimpleMailMessage();
	msg.setTo("etendermsegs@gmail.com");
	msg.setSubject("testing "+rand);
	msg.setText("Main test body "+rand);
	sender.send(msg);
	
	System.out.println("....after send");

return "test1";
}


public void sendMail(){
	System.out.println("Send mail");



}

}
