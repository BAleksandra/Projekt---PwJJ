package com.projektpk.szukajpracy.mail;

import com.projektpk.szukajpracy.Model.MessageEnty;

import javax.mail.MessagingException;
//mailDao
public interface  MailRepository  {

    int insertMess(MessageEnty mess) throws MessagingException;

    default int sendMessage(MessageEnty mess) throws MessagingException{
        return insertMess(mess);
    }
}

