package com.promomailer.Services;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.promomailer.Helpers.EmailHelper;
import com.promomailer.Models.EmailRequest;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class MicroserviceApp {

    private static final String HTML_FILE_PATH = "Promotional-Content.html";

    public static void main(String[] args) {

        // app port
        port(7000);

        post("/upload", (req, res) -> {
            try {
                InputStream inputStream = req.raw().getInputStream();
                FileOutputStream outputStream = new FileOutputStream(HTML_FILE_PATH);

                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                inputStream.close();
                outputStream.close();

                return "File uploaded and saved as Promotional-Content.html";
            } catch (Exception e) {
                e.printStackTrace();
                res.status(500);
                return "File upload failed";
            }
        });

        post("/send-email", (req, res) -> {
            try {
                Gson gson = new Gson();
                EmailRequest emailRequest = gson.fromJson(req.body(), EmailRequest.class);

                if (emailRequest.getSubject() != null && emailRequest.getEmail() != null) {
                    // Read the file content
                    String filePath = "Promotional-Content.html";
                    String htmlContent;

                    try {
                        htmlContent = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
                    } catch (Exception e) {
                        e.printStackTrace();
                        res.status(500);
                        return "Failed to read file content";
                    }
                    // Extract values from the request body
                    Map<String, String> replacements = new HashMap<>();
                    replacements.put("[CIF]", emailRequest.getCif());
                    replacements.put("[Account Number]", emailRequest.getAccountNumber());
                    replacements.put("[IBAN]", emailRequest.getIban());
                    replacements.put("[PIN]", emailRequest.getPin());

                    String filledHtmlContent = EmailHelper.fillInCustomerData(htmlContent, replacements);

                    EmailHelper.sendEmail(emailRequest.getEmail(), emailRequest.getSubject(), filledHtmlContent);
                    return "Email sent successfully!";
                } else {
                    res.status(400);
                    return "Missing required fields or no HTML file uploaded.";
                }
            } catch (Exception e) {
                res.status(500);
                System.out.println(e);
                return "Failed to send email.";
            }
        });

    }

}