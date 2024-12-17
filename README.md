# TagDrive - A Tag-Based File Management System

TagDrive is a modern file management system where users can upload, download, and organize files using **tags** instead of traditional folders. Designed for scalability and performance, it leverages a microservices architecture and a ReactJS-based frontend to deliver a seamless experience.

## Tech Stack

### Backend
- **Spring Boot**: RESTful microservices development.
- **Spring Cloud**: API Gateway and Service Discovery for routing and dynamic service management.
- **RabbitMQ**: Event-driven communication.
- **MongoDB**: Flexible and efficient NoSQL database.
- **AWS S3**: Scalable and secure file storage.

### Frontend (In Progress)
- **ReactJS**: Modern frontend framework for dynamic user interfaces.
- **TypeScript**: Ensures type safety and enhances maintainability.

### Architecture
- **Microservices**: Decoupled services for scalability and resilience.
- **API Gateway**: Centralized entry point for routing requests.
- **Service Discovery**: Enables dynamic management of backend services.

## Core Features

- **Tag-Based Organization**: Files are categorized and retrieved based on tags.
- **Upload & Download**: Users can upload files to AWS S3 and retrieve them efficiently.
- **Scalable & Event-Driven**: RabbitMQ ensures asynchronous processing for smooth workflows.
- **Service Discovery**: Dynamic service management using Spring Cloud Discovery.
- **Centralized Gateway**: API Gateway for routing and managing microservice endpoints.
- **Interactive UI**: ReactJS with TypeScript for a clean, user-friendly experience.
