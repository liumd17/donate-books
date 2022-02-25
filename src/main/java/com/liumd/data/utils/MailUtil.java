package com.liumd.data.utils;

import com.sun.mail.util.MailSSLSocketFactory;
import lombok.SneakyThrows;

import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author liumuda
 * @date 2022/2/25 10:22
 */
public class MailUtil {
    // qq邮件服务器
    public static final String MAIL_HOST = "smtp.qq.com";
    // 邮箱发送协议
    public static final String MAIL_TRANSPORT_PROTOCOL = "smtp";
    // 需要验证用户名密码
    public static final String MAIL_STML_AUUUTH = "true";
    // 发送者邮箱
    public static final String MAIL_TUSERNAME = "1161859914@qq.com";
    // POP3授权码
    public static final String MAIL_POP3_PASSWORD = "qgvhotcrrkgvgjbc";
    // IMAP授权码
    public static final String MAIL_IMAP_PASSWORD = "ksyucpyeydoljfje";

    @SneakyThrows
    public static Boolean sendMails(String mailbox, String subject, String content) {
        Properties prop=new Properties();
        prop.setProperty("mail.host", MAIL_HOST);//设置qq邮件服务器
        prop.setProperty("mail.transport.protocol",MAIL_TRANSPORT_PROTOCOL);//邮箱发送协议
        prop.setProperty("mail.stmp.auuuth",MAIL_STML_AUUUTH);//需要验证用户名密码

        //设置SSL加密
        MailSSLSocketFactory sf=new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable","true");
        prop.put("mail.smtp.ssl.socketFactory",sf);

        //1 创建定义整个应用程序所需环境信息的session 对象

        Session session=Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MAIL_TUSERNAME, MAIL_POP3_PASSWORD);

            }
        });

        //开启debug模式，开启后会在控制台打印邮件发送过程中的参数信息，不开启则不显示
        session.setDebug(true);
        //2 通过session得到transport对象
        Transport ts = session.getTransport();

        //3 使用邮箱的用户名和授权码连上邮件服务器
        ts.connect(MAIL_HOST, MAIL_TUSERNAME, MAIL_POP3_PASSWORD);

        //4 创建邮件，写邮件
        MimeMessage message=new MimeMessage(session);
        //指明发件人
        message.setFrom(new InternetAddress(MAIL_TUSERNAME));
        //收件人，我这里是自己给自己发送
        message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(mailbox)});
        //邮件主题
        message.setSubject(subject);
        //邮件内容
        message.setContent(content,"text/html;charset=UTF-8");

        //5 发送邮件
        ts.sendMessage(message,message.getAllRecipients());

        //6 关闭邮件
        ts.close();

        return Boolean.TRUE;
    }

}
