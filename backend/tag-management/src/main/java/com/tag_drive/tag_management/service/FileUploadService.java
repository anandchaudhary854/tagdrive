package com.tag_drive.tag_management.service;

import com.tag_drive.tag_management.dto.FileUploadMessage;
import com.tag_drive.tag_management.dto.FileUploadRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {
    void uploadFile(String tagName, MultipartFile file) throws IOException;
    void pushFileToS3(FileUploadMessage fileUploadMessage) throws InterruptedException;
}
