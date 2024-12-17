package com.tag_drive.tag_management.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.tag_drive.tag_management.Utils.UploadUtil;
import com.tag_drive.tag_management.config.rabbitMQConfig.FileUploadMessageProducer;
import com.tag_drive.tag_management.dto.FileUploadMessage;
import com.tag_drive.tag_management.dto.FileUploadRequest;
import com.tag_drive.tag_management.service.FileUploadService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${aws.bucket.name}")
    private String bucketName;
    @Autowired
    FileUploadMessageProducer fileUploadMessageProducer;

    @Autowired
    UploadUtil uploadUtil;


    private final AmazonS3 s3Client;

    public FileUploadServiceImpl(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public void uploadFile(String tagName, MultipartFile multipartFile) throws IOException {
        FileUploadRequest fileUploadRequest = new FileUploadRequest();
        String relativeFilePath = uploadUtil.saveFile(multipartFile);
        fileUploadRequest.setId(String.valueOf(UUID.randomUUID()));
        fileUploadRequest.setFileName(multipartFile.getOriginalFilename());
        fileUploadRequest.setFilePath(relativeFilePath);
        fileUploadRequest.setTagName(tagName);
        System.out.println("FileUploadServiceImpl: File upload request" + fileUploadRequest.getFileName());
        System.out.println(multipartFile.getContentType());
        fileUploadMessageProducer.sendFileUploadMessage(fileUploadRequest);
    }

    @Override
    public void pushFileToS3(FileUploadMessage fileUploadMessage) throws InterruptedException {
        Thread.sleep(1500);
        System.out.println("FileUploadServiceImpl: Pushing file to S3: " + fileUploadMessage.getFileName());

        String fileName = fileUploadMessage.getFileName();
        String relativePath = fileUploadMessage.getFilePath();

        // Resolve file path
        Path filePath = relativePath.endsWith(fileName)
                ? Paths.get(relativePath)
                : Paths.get(relativePath, fileName);

        // Convert to File
        File file = filePath.toFile();

        // Check if file exists
        if (!file.exists() || !file.isFile()) {
            throw new RuntimeException("File not found or inaccessible: " + filePath.toString());
        }

        try {
            // Dynamically determine MIME type
            String mimeType = Files.probeContentType(filePath);
            if (mimeType == null) {
                mimeType = "application/octet-stream"; // Default MIME type
            }

            // Create PutObjectRequest
            PutObjectRequest request = new PutObjectRequest(bucketName, fileName, file);

            // Add Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(mimeType);
            metadata.setContentLength(file.length());
//            metadata.addUserMetadata("Title", "File Upload - " + fileName);
            request.setMetadata(metadata);

            // Upload the file to S3
            s3Client.putObject(request);
            System.out.println("File uploaded successfully to S3: " + fileName);

            // Delete file after upload
            uploadUtil.deleteFile(fileUploadMessage.getFilePath(), fileUploadMessage.getFileName());

        } catch (IOException e) {
            throw new RuntimeException("Error determining file type: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error uploading file to S3: " + e.getMessage(), e);
        }
    }



}
