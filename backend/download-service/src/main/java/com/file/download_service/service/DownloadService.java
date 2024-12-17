package com.file.download_service.service;

import com.file.download_service.entity.DownloadRequest;
import com.file.download_service.repository.DownloadRepository;
import com.file.download_service.utils.enums.DownloadStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class DownloadService {

    private final DownloadRepository downloadRepository;

    public DownloadService(DownloadRepository downloadRepository){
        this.downloadRepository = downloadRepository;
    }

    public void startDownload(String url){
        DownloadRequest downloadRequest = new DownloadRequest();
        downloadRequest.setUrl(url);
        downloadRequest.setStatus(DownloadStatus.IN_PROGRESS);
        downloadRequest.setPriority(0);
        LocalDateTime localDateTime = LocalDateTime.now();
        downloadRequest.setCreatedAt(localDateTime);
        downloadRepository.save(downloadRequest);
    }

}
