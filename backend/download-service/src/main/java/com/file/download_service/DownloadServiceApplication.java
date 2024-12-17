package com.file.download_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DownloadServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DownloadServiceApplication.class, args);
	}

}