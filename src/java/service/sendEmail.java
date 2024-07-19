package service;

import java.util.Date;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class sendEmail {
    static final String from = "heartstealshop@gmail.com";
    static final String password = "cjzs ttsf yyzg tizo";

    public static boolean sendEmail(String user, String to, String verificationCode) {
        // Properties : khai báo các thuộc tính
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
        props.put("mail.smtp.port", "587"); // TLS 587 SSL 465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // TODO Auto-generated method stub
                return new PasswordAuthentication(from, password);
            }
        };
        // Phiên làm việc
        Session session = Session.getInstance(props, auth);
        // Tạo một tin nhắn
        MimeMessage msg = new MimeMessage(session);

        try {
            // Kiểu nội dung
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            // Người gửi
            msg.setFrom(from);
            // Người nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            // Tiêu đề email
            msg.setSubject("Verify Your Email Address");
            // Quy đinh ngày gửi
            msg.setSentDate(new Date());
            // Quy định email nhận phản hồi
            // msg.setReplyTo(InternetAddress.parse(from, false))

            // Nội dung
            String htmlContent = "<html>" +
                    "<body>" +
                    "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: auto; padding: 20px; border: 1px solid #e0e0e0;'>" +
                    "<h2 style='color: #333;'>Mã xác minh của bạn</h2>" +
                    "<p>Gửi " + user + ",</p>" +
                    "<p>Mã xác minh của bạn là:</p>" +
                    "<h1 style='text-align: center;'>" + verificationCode + "</h1>" +
                    "<p>Vui lòng sử dụng mã này để hoàn tất đăng ký của bạn.</p>" +
                    "<p>Trân trọng,<br>Heartsteal</p>" +
                    "<hr>" +
                    "<p style='font-size: 0.9em; color: #777;'>Nếu bạn không yêu cầu mã này, vui lòng bỏ qua email này.</p>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

            msg.setContent(htmlContent, "text/HTML;");
            // Gửi email
            Transport.send(msg);
//            System.out.println("gui thanh cong");
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }
    
    public static void main(String[] args) {
        sendEmail.sendEmail("minh_giang","minhhien30201@gmail.com", "123");
    }
}
