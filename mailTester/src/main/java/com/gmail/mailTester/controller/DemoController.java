package com.gmail.mailTester.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.google.api.client.util.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;



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

@GetMapping("progresstest")
public ResponseEntity<ResponseBodyEmitter> prog(Model theModel) throws IOException {   
//    final ResponseBodyEmitter emitter = new ResponseBodyEmitter();
//    ExecutorService service = Executors.newSingleThreadExecutor();
//    service.execute(() -> {
//        for (int i = 0; i < 1000; i++) {
//            try {
//                emitter.send(i + " - ", MediaType.TEXT_PLAIN);
//
//                Thread.sleep(1000);
//            } catch (Exception e) {
//                e.printStackTrace();
//                emitter.completeWithError(e);
//                return;
//            }
//        }
//        emitter.complete();
//    });
//
//    return emitter;
    final ResponseBodyEmitter emitter = new ResponseBodyEmitter();
    ExecutorService service = Executors.newSingleThreadExecutor();
    service.execute(() -> {
        for (int i = 0; i < 1000; i++) {
            try {
                emitter.send(i + " - ", MediaType.TEXT_PLAIN);

                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
                emitter.completeWithError(e);
                return;
            }
        }
        emitter.complete();
    });

    return new ResponseEntity(emitter, HttpStatus.OK);
}

	public void callsms() {
    
	    System.out.println("sms sent!");

	}

	
    @GetMapping("mailgun")
    public static String sendSimpleMessage() throws UnirestException {
    
            String YOUR_DOMAIN_NAME ="sandboxf17bf299aa0648199b22b3e77bebeb54.mailgun.org";
            String API_KEY= "key-b70dd74d11ab5b2e80b4f6281786d8e4";
            String FROM = "etender"+"@"+YOUR_DOMAIN_NAME;
          
        	
                HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + YOUR_DOMAIN_NAME + "/messages")
                        .basicAuth("api", API_KEY)
                        .field("from", FROM)
                        .field("to", "thanpuia46@gmail.com")
                        .field("subject", "hello: ")
                        .field("text", "testing: ")
                        .asJson();
                
                    request.getBody();
                    
               System.out.println("Mail gun used");
        	
            

            
            return "index";
    	}
    
    //GMAIL API START
    //*****
    //*****
    //*****

//    public static MimeMessage createEmail(String to,
//            String from,
//            String subject,
//            String bodyText)
//            throws MessagingException {
//            Properties props = new Properties();
//            Session session = Session.getDefaultInstance(props, null);
//            
//            MimeMessage email = new MimeMessage(session);
//            
//            email.setFrom(new InternetAddress(from));
//            email.addRecipient(javax.mail.Message.RecipientType.TO,
//            new InternetAddress(to));
//            email.setSubject(subject);
//            email.setText(bodyText);
//            return email;
//    }
//    
//    public static Message createMessageWithEmail(MimeMessage emailContent)
//            throws MessagingException, IOException {
//        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//        emailContent.writeTo(buffer);
//        byte[] bytes = buffer.toByteArray();
//        String encodedEmail = Base64.encodeBase64URLSafeString(bytes);
//        Message message = new Message();
//        message.setRaw(encodedEmail);
//        return message;
//    }
//
//    public static Message sendMessage(Gmail service,
//            String userId,
//            MimeMessage emailContent)
//        throws MessagingException, IOException {
//        Message message = createMessageWithEmail(emailContent);
//        message = service.users().messages().send(userId, message).execute();
//        
//        System.out.println("Message id: " + message.getId());
//        System.out.println(message.toPrettyString());
//        return message;
//   }
//
//    @GetMapping("gmail")
//    public String gmail() {
//	
//	String TO = "thanpuia46@gmail.com";
//	
//	MimeMessage myMsg = createEmail(TO, TO, "Subj", "body");
//	
//	//sendMessage()
//	
//	
//	return "";
//    }

    
    
    //*****
    //*****
    //*****
    //GMAIL API STOP
    
}

