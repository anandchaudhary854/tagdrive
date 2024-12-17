package com.file.download_service.controller;

import com.file.download_service.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/download-service")
public class DownloadContorller {

    @Autowired
    DownloadService service;

    @PostMapping("/start/{url}")
    public ResponseEntity<String> downloadFile(@PathVariable String url){
        service.startDownload(url);
        return ResponseEntity.ok("Download started for: " + url);
    }

    @GetMapping("/progress/{id}")
    public String fetchDownloadProgress(@PathVariable String id){
        if(!id.isEmpty()){
            System.out.println(id);
            return "In progress";
        }
        return "ID not found";
    }
}
