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

### Clone the Repository

```bash
git clone https://github.com/shaikho/promotional-mailer.git
cd promotional-mailer

### Build and Run the Application

1.  Use Maven to compile the application:bashCopy codemvn clean compile
    
2.  After compiling, you can run the application using the Maven exec plugin:bashCopy codemvn exec:java -Dexec.mainClass=com.promomailer.MicroserviceAppThis will start the server, and you can access the endpoints as described in the API documentation.
    

### API Documentation

The Swagger UI is available for interacting with the API. You can access it at:

Plain textANTLR4BashCC#CSSCoffeeScriptCMakeDartDjangoDockerEJSErlangGitGoGraphQLGroovyHTMLJavaJavaScriptJSONJSXKotlinLaTeXLessLuaMakefileMarkdownMATLABMarkupObjective-CPerlPHPPowerShell.propertiesProtocol BuffersPythonRRubySass (Sass)Sass (Scss)SchemeSQLShellSwiftSVGTSXTypeScriptWebAssemblyYAMLXML`   bashCopy codehttp://localhost:4567/swagger   `

### Deploying the Application

To deploy the application, follow these steps:

1.  Create a deployable JAR file using Maven:bashCopy codemvn clean packageThe JAR file will be located in the target directory (e.g., target/promomailer-1.0-SNAPSHOT.jar).
    
2.  You can run the JAR file on the server using the following command:bashCopy codejava -jar target/promomailer-1.0-SNAPSHOT.jarMake sure that the server has Java 1.8 installed.
    
3.  Ensure that the server environment is configured with the necessary Java version and network settings. Update any configuration files or environment variables as needed.