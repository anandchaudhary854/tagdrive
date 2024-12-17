package com.file.download_service.repository;

import com.file.download_service.entity.DownloadRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DownloadRepository extends MongoRepository<DownloadRequest, String> {
}
