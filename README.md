# Promotional Mailer API

## Overview

Promotional Mailer is a Java-based microservice for uploading HTML files and sending promotional emails. It uses Spark as the web framework and Swagger for API documentation.

## Features

- Upload an HTML file to be used as email content.
- Send an email using the uploaded HTML content with dynamic placeholders.
- Placeholders include `[CIF]`, `[Account Number]`, `[IBAN]`, `[PIN]` and their equivalents in Arabic.

## Technologies Used

- Java 1.8
- Spark Framework
- Maven
- Swagger for API Documentation
- Mailtrap for Email Testing

## Setup Instructions

### Prerequisites

- JDK 1.8+
- Maven
- SMTP server for email sending

## Setup Instructions

- git clone https://github.com/shaikho/promotional-mailer.git
- cd promotional-mailer


### Configure the Server

- Ensure that the server environment is configured with the necessary Java version and network settings. Update any configuration files or environment variables as needed.

### Update SMTP Server Configurations

- Before deploying or running the application, make sure to update the SMTP server configurations in the EmailHelper class. Change the SMTP server, port, username, and password to match your email service provider's settings.

### Build and Run the Application

1.  Use Maven to compile the application using  mvn clean compile
    
2.  After compiling, you can run the application using the Maven exec plugin:bash mvn exec:java -Dexec.mainClass=com.promomailer.Services.MicroserviceAppThis will start the server, and you can access the endpoints as described in the API documentation.
    
### Deploying the Application

To deploy the application, follow these steps:

1.  Create a deployable JAR file using Maven:bash mvn clean packageThe JAR file will be located in the target directory (e.g., target/promomailer-1.0-SNAPSHOT.jar).
    
2.  You can run the JAR file on the server using the following command:bash java -jar target/promomailer-1.0-SNAPSHOT.jarMake sure that the server has Java 1.8 installed.