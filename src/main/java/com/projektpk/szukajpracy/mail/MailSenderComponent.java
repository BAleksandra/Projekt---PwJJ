package com.projektpk.szukajpracy.mail;

import com.projektpk.szukajpracy.Model.MessageEnty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
@Repository("sm")
public class MailSenderComponent  implements MailRepository{

    private static List<MessageEnty> DB = new ArrayList<>();

    @Autowired
    private JavaMailSender javaMailSender;
/*
wysyla wiadmosc
 */
    @Override
    public int insertMess(MessageEnty mess) throws MessagingException{

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;

        helper = new MimeMessageHelper(message, true); // true indicates
        // multipart message
        helper.setSubject(mess.getTitle());
        helper.setTo(mess.getMailSender());

        helper.setText(mess.getTextMessage(), true); // true indicates html
        // continue using helper object for more functionalities like adding attachments, etc.

        javaMailSender.send(message);
        DB.add(new MessageEnty(mess.getMailSender(), mess.getTitle(), mess.getTextMessage()));
        return 1;
    }


}
