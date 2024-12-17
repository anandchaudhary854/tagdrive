package com.tag_drive.tag_management.Utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class UploadUtil {

    private final String uploadDir = "uploadFiles"; // Default upload directory

    /**
     * Save the uploaded file to the specified directory and return its relative path.
     *
     * @param multipartFile the file to be saved
     * @return the relative path of the saved file
     * @throws IOException if an error occurs during file storage
     */
    public String saveFile(MultipartFile multipartFile) throws IOException {
        // Ensure the upload directory exists
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Generate unique file name
        String fileName = multipartFile.getOriginalFilename();

        // Create file path
        assert fileName != null;
        Path filePath = uploadPath.resolve(fileName);

        // Save the file to disk
        Files.write(filePath, multipartFile.getBytes());

        // Return the relative file path
        return uploadDir + "/" + fileName;
    }

    public boolean deleteFile(String relativePath, String fileName) {
        try {
            // Construct the file path
            Path filePath = relativePath.endsWith(fileName)
                    ? Paths.get(relativePath)
                    : Paths.get(relativePath, fileName);

            File file = filePath.toFile();

            // Log the canonical path
            System.out.println("Canonical file path: " + file.getCanonicalPath());

            // Check if the file exists
            if (!file.exists()) {
                System.err.println("File not found at path: " + file.getCanonicalPath());
                return false;
            }

            // Attempt to delete the file
            boolean deleted = file.delete();
            if (!deleted) {
                System.err.println("Failed to delete file. Check permissions or locks on the file.");
            }
            return deleted;
        } catch (Exception e) {
            System.err.println("Error during file deletion: " + e.getMessage());
            return false;
        }
    }
}
