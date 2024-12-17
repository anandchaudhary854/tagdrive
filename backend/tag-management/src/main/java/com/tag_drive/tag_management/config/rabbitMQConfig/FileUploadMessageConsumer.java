package com.tag_drive.tag_management.config.rabbitMQConfig;

import com.tag_drive.tag_management.dto.FileUploadMessage;
import com.tag_drive.tag_management.service.FileUploadService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class FileUploadMessageConsumer {
    private final FileUploadService fileUploadService;

    public FileUploadMessageConsumer(FileUploadService fileUploadService){
        this.fileUploadService = fileUploadService;
    }

    @RabbitListener(queues = "fileUploadQueue")
    public void consumeFileUploadMessage(FileUploadMessage fileUploadMessage) throws InterruptedException {
        fileUploadService.pushFileToS3(fileUploadMessage);
    }
}
