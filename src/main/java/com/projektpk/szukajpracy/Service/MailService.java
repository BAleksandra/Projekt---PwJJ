package com.projektpk.szukajpracy.Service;

import com.projektpk.szukajpracy.Model.*;
import com.projektpk.szukajpracy.mail.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class MailService {

    private final MailRepository mr;

    @Autowired
    public MailService(@Qualifier("sm") MailRepository mr) {
        this.mr = mr;
    }

    public int sendMessage(MessageEnty mess) throws MessagingException
    {
        return mr.insertMess(mess);
    }
}
