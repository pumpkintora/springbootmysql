Chat Application Backend

This is the backend server for the Chat Application, built using Spring Boot. It handles user authentication, WebSocket communication, and message broadcasting through Kafka.
Prerequisites

    Java 17 or later
    Maven
    Kafka
    Zookeeper

Setup

    Clone the repository:

    bash

git clone <repository-url>
cd chat-application-backend

Configure Kafka: Ensure Kafka and Zookeeper are running on your machine. The default configuration assumes Kafka is running on localhost:9092.

Configure Database: Update the application.properties file with your database configuration.

Build and Run the Application:

bash

    mvn clean install
    mvn spring-boot:run

Summary of Components
Security Configuration

    Purpose: Secures the application endpoints, allowing only authenticated requests except for login and signup.
    Endpoints:
        /api/auth/signup and /api/auth/login are public.
        All other endpoints require authentication.
    CORS: Configured to allow cross-origin requests from the frontend.

WebSocket Configuration

    Endpoint: /ws for WebSocket connections.
    Allowed Origins: Configured to allow connections from http://localhost:3000.
    Message Broker: Configured to enable a simple broker at /topic and application destination prefixes at /app.

Models

    User: Represents users of the application.
    ChatRoom: Represents chat rooms in the application.
    ChatMessage: Represents messages sent in chat rooms. Contains chatroomId, userId, and content.

Kafka Integration

    Producer Service: Serializes ChatMessage objects and sends them to a Kafka topic.
    Consumer Service: Listens to the Kafka topic and processes incoming messages.

REST API Endpoints
Authentication

    POST /api/auth/signup: Registers a new user.
    POST /api/auth/login: Authenticates a user and returns a JWT token and user details.

Chat

    POST /api/chat/send: Sends a chat message, including chatroomId, userId, and message content.
