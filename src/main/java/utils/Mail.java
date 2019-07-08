package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class Mail {

    private static final Logger logger = LogManager.getLogger(Mail.class.getName());

    @Autowired
    private MailSender mailSender;

    public boolean sendMail(String to, String subject, String body)
    {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }

    }
}
