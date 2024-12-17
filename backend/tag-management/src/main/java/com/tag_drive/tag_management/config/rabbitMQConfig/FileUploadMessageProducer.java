package com.tag_drive.tag_management.config.rabbitMQConfig;

import com.tag_drive.tag_management.dto.FileUploadMessage;
import com.tag_drive.tag_management.dto.FileUploadRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FileUploadMessageProducer {
    private final RabbitTemplate rabbitTemplate;

    public FileUploadMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendFileUploadMessage(FileUploadRequest fileUploadRequest){
        FileUploadMessage fileUploadMessage = new FileUploadMessage();
        fileUploadMessage.setId(String.valueOf(UUID.randomUUID()));
        fileUploadMessage.setFileName(fileUploadRequest.getFileName());
        fileUploadMessage.setTagName(fileUploadRequest.getTagName());
        fileUploadMessage.setFilePath(fileUploadRequest.getFilePath());
        rabbitTemplate.convertAndSend("fileUploadQueue", fileUploadMessage);
    }
}
