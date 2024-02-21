package com.example.webclient.serivices;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway(defaultRequestChannel = "textInputChanel")
public interface FileWriter {
    void writeToFile(@Header(FileHeaders.FILENAME) String filename, String data);
}
