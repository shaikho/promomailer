package com.promomailer.Helpers;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailHelper {

  public static void sendEmail(String to, String subject, String htmlContent) throws MessagingException {

    // Mailtrap SMTP server configuration
    String host = "smtp.mailtrap.io";
    String username = "c3a12ec5e6b9af";
    String password = "5c603d83f91e2c";
    Properties properties = new Properties();
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", host);
    properties.put("mail.smtp.port", "587");

    // Create session with SMTP authentication
    Session session = Session.getInstance(properties, new Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
      }
    });

    // Convert HTML content to UTF-8
    byte[] bytes = htmlContent.getBytes(StandardCharsets.UTF_8);
    String utf8Content = new String(bytes, StandardCharsets.UTF_8);

    // Compose the email
    MimeMessage message = new MimeMessage(session);
    message.setFrom(new InternetAddress(username));
    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    message.setSubject(subject);

    // Set content type to HTML with UTF-8 encoding
    message.setContent(utf8Content, "text/html; charset=UTF-8");

    // Send the email
    Transport.send(message);
  }

  // Fills in the customer data in the HTML content
  public static String fillInCustomerData(String htmlContent, Map<String, String> replacements) {
    for (Map.Entry<String, String> entry : replacements.entrySet()) {
      String placeholder = Pattern.quote(entry.getKey());
      String value = entry.getValue();
      htmlContent = htmlContent.replaceAll(placeholder, Matcher.quoteReplacement(value));
    }
    return htmlContent;
  }

}
