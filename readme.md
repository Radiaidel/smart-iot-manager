# Smart IoT Manager

A robust IoT device management system built with Spring Boot and MongoDB.

## 🚀 Features

- **Device Management**: Track and manage IoT devices across different zones
- **Real-time Monitoring**: Collect and analyze device measurements
- **Secure Authentication**: JWT-based authentication and role-based access control
- **Zone Organization**: Group devices by zones for better organization
- **Alert System**: Automated alerts based on device measurements
- **REST API**: Comprehensive API for system integration

## 🛠 Technology Stack

- **Backend**: Spring Boot 3.1.5
- **Security**: Spring Security with JWT
- **Database**: MongoDB
- **Documentation**: OpenAPI (Swagger)
- **Build Tool**: Maven
- **Java Version**: 17
- **Containerization**: Docker
- **CI/CD**: Jenkins Pipeline
- **Code Quality**: SonarLint

## 📋 Prerequisites

- JDK 17 or later
- MongoDB 4.4+
- Maven 3.6+
- Docker 20.10+
- Jenkins 2.375+

## 🔧 Configuration

### Environment Variables

```properties
MONGODB_URI=your_mongodb_connection_string
JWT_SECRET=your_jwt_secret_key
JWT_EXPIRATION=86400000
DOCKER_USERNAME=your_dockerhub_username
DOCKER_PASSWORD=your_dockerhub_password
```

### Application Properties

The application uses YAML configuration with different profiles:
- `application.yml`: Default configuration
- `application-dev.yml`: Development environment
- `application-prod.yml`: Production environment

## 🚀 Getting Started

1. **Clone the repository**
```bash
git clone https://github.com/Radiaidel/smart-iot-manager.git
cd smart-iot-manager
```

2. **Configure MongoDB**
```bash
# Start MongoDB service
sudo service mongod start
```

3. **Build the application**
```bash
mvn clean install
```

4. **Run the application**
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8087/api`

## 🔒 Security

- JWT-based authentication
- Role-based access control (USER, ADMIN)
- Secure password hashing with BCrypt
- Protected API endpoints

## 📚 API Documentation

Access the Swagger UI documentation at:
```
http://localhost:8080/api/swagger-ui.html
```

### Main Endpoints

- **Authentication**
    - POST `/api/auth/login`: User login
    - POST `/api/auth/register`: User registration

- **Devices**
    - GET `/api/user/devices`: List all devices
    - GET `/api/user/devices/zone/{zoneId}`: Get devices by zone
    - POST `/api/admin/devices`: Create new device (Admin only)

## 🏗 Project Structure

```
src/main/java/com/iot/smartiotmanager/
├── controller/          # REST controllers
├── dto/                 # Data Transfer Objects
│   ├── request/        # Request DTOs
│   └── response/       # Response DTOs
├── model/              # Domain models
├── repository/         # MongoDB repositories
├── security/           # Security configurations
│   └── jwt/           # JWT implementation
├── service/           # Business logic
└── exception/         # Custom exceptions
```

## 🔍 Monitoring

The application includes Spring Boot Actuator endpoints for monitoring:
- `/actuator/health`: System health information
- `/actuator/info`: Application information
- `/actuator/metrics`: Application metrics

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 👥 Authors

- Rayane Fiach - [Rayane's Github](https://github.com/Rayane20777)
- Anouar Belhassan - [Anouar's Github](https://github.com/BelAnouar)
- Idelkadi Radia - [Radia's Github](https://github.com/Radiaidel)

## 🙏 Acknowledgments

- Spring Boot team for the amazing framework
- MongoDB team for the robust database
- All contributors who have helped this project grow