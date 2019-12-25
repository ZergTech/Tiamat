package com.zerg.tiamat.common.utils;

import com.zerg.tiamat.common.exception.BizException;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author : xuyang
 * @date : 2019-12-19 14:25
 */


public class MailUtils {
    private static final String USER = "qq786556830@163.com";
    private static final String PWD = "xxxxxx1";
    private static final String NICK_NAME = "行情震荡报警";

    /**
     * 发送邮件
     * @param mailTo 发送人的emai地址
     * @param subject 主题
     * @param text 内容
     */
    public static void sendEmail(String mailTo, String subject, String text) {
        Properties props = buildMailProps();
        Session session = buildMailSession(props);
        MimeMessage message = buildMailMessage(mailTo, subject, text, session);
        sendEmail(session, message);
    }

    private static void sendEmail(Session session, MimeMessage message) {
        try {
            Transport ts = session.getTransport();
            // 密码为授权码不是邮箱的登录密码
            ts.connect(USER, PWD);
            ts.sendMessage(message, message.getAllRecipients());
        }catch (Exception e){
            throw new BizException("发送邮件异常", e);
        }

    }

    private static Session buildMailSession(Properties props) {
        //发送邮件时使用的环境配置
        Session session = Session.getInstance(props);
        session.setDebug(false);
        return session;
    }

    private static Properties buildMailProps() {
        //key value:配置参数。真正发送邮件时再配置
        Properties props = new Properties();
        //指定邮件发送的协议，参数是规范规定的
        props.setProperty("mail.transport.protocol", "smtp");
        //指定发件服务器的地址，参数是规范规定的
        props.setProperty("mail.host", "smtp.163.com");
        //邮件发送的调试模式，参数是规范规定的
        props.setProperty("mail.debug", "false");
        //请求服务器进行身份认证。参数与具体的JavaMail实现有关
        props.setProperty("mail.smtp.auth", "true");
        return props;
    }

    private static MimeMessage buildMailMessage(String mailTo, String subject, String text, Session session) {
        MimeMessage message = new MimeMessage(session);
        try {
            //设置邮件的头
            message.setFrom(new InternetAddress(USER, NICK_NAME, "UTF-8"));
            message.setRecipients(Message.RecipientType.TO, mailTo);
            message.setSubject(subject);
            //设置正文
            message.setContent(text, "text/html;charset=gbk");
            message.saveChanges();
        }catch (Exception e){
            throw new BizException("构造邮件对象异常", e);
        }
        return message;
    }
}
