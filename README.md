# TagDrive - A Tag-Based File Management System

TagDrive is a modern file management system where users can upload, download, and organize files using **tags** instead of traditional folders. Designed for scalability and performance, it leverages a microservices architecture to deliver a seamless experience.

## Tech Stack

- **Backend**: Spring Boot, Spring Cloud (API Gateway, Service Discovery)
- **Message Queue**: RabbitMQ for event-driven communication
- **Database**: MongoDB for flexible and efficient data storage
- **File Storage**: AWS S3 for scalable and secure file storage
- **Architecture**: Microservices-based with service discovery and centralized API gateway

## Core Features

- **Tag-Based Organization**: Files are categorized and retrieved based on tags.
- **Upload & Download**: Users can upload files to AWS S3 and retrieve them efficiently.
- **Scalable & Event-Driven**: RabbitMQ ensures asynchronous processing for smooth workflows.
- **Service Discovery**: Dynamic service management using Spring Cloud Discovery.
- **Centralized Gateway**: API Gateway for routing and managing microservice endpoints.
