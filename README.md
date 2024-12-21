# 🌟 Smart IoT Manager

A secure REST API for managing IoT sensors infrastructure built with Spring Boot, featuring stateless authentication and role-based access control.

## 🌟 Features

### Device Management
- List all devices with pagination (USER/ADMIN)
- Search devices by zone with pagination (USER/ADMIN)
- Add new devices (ADMIN only)
- Monitor device status and last communication time

### Zone Management
- View zone details (USER/ADMIN)
- Create new zones (ADMIN only)
- Manage device-zone relationships

### Measurement Management
- Record and retrieve measurements (USER/ADMIN)
- Export measurement data (USER/ADMIN)
- Paginated measurement history per device
- Support for temperature and humidity sensors

### Alert System
- Automated alert generation based on thresholds
- Multiple severity levels (NORMAL, LOW, MEDIUM, HIGH, CRITICAL)
- Customized alert messages per sensor type
- Alert history tracking

### User Management
- Secure user authentication with JWT
- Role-based access control (USER/ADMIN)
- User registration and login
- Role management for administrators

## 🚀 Technology Stack

- **Backend Framework**: Spring Boot 3.4.0
- **Security**: Spring Security with JWT
- **Database**: MongoDB
- **Documentation**: SpringDoc OpenAPI
- **Build Tool**: Maven
- **Testing**: JUnit, Mockito
- **Monitoring**: Spring Actuator
- **Development Tools**: Lombok, MapStruct
- **Quality Assurance**: SonarLint
- **Container Platform**: Docker
- **CI/CD**: Jenkins, GitHub Actions

## 📋 Prerequisites

- JDK 17
- Maven
- MongoDB
- Docker (for containerization)
- Git

## 🛠️ Installation & Setup

1. Clone the repository:
```bash
git clone [repository-url]
cd smart-iot-manager
```

2. Configure MongoDB connection in `application.properties`:
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/smart_iot_manager
```

3. Build the project:
```bash
mvn clean install
```

4. Run the application:
```bash
mvn spring-boot:run
```

## 🔒 Security Thresholds

### Temperature Sensors
- CRITICAL: > 40°C or < -10°C
- HIGH: 35-40°C or -5°C to -10°C
- MEDIUM: 30-35°C or 0°C to -5°C
- LOW: 25-30°C
- NORMAL: 20-25°C

### Humidity Sensors
- CRITICAL: > 90% or < 20%
- HIGH: 80-90% or 20-30%
- MEDIUM: 70-80% or 30-40%
- LOW: 65-70% or 40-45%
- NORMAL: 45-65%

## 🌐 API Endpoints

### Authentication
- `POST /api/auth/login` - User authentication
- `POST /api/auth/register` - User registration

### Device Management
- `GET /api/user/devices` - List devices (paginated)
- `GET /api/user/devices/zone/{zoneId}` - Get devices by zone
- `POST /api/admin/devices` - Add new device

### Zone Management
- `GET /api/user/zones/{id}` - Get zone details
- `POST /api/admin/zones` - Create new zone

### Measurement Management
- `GET /api/user/measurements` - List measurements
- `POST /api/user/measurements` - Record measurement
- `GET /api/user/measurements/export` - Export measurements
- `GET /api/user/measurements/device/{deviceId}` - Get device measurements

### Alert Management
- `GET /api/user/alerts` - List alerts

### User Management
- `GET /api/admin/users` - List users (ADMIN only)
- `PUT /api/admin/users/{id}/roles` - Manage user roles (ADMIN only)

## 🚀 CI/CD Pipeline

The project uses a two-stage CI/CD pipeline:

1. **GitHub Actions** (First Filter):
   - Unit tests
   - Code syntax validation
   - Style convention checks
   - Dependency vulnerability scan
   - Build verification

2. **Jenkins Pipeline**:
   - Comprehensive testing
   - Code quality analysis
   - Docker image creation
   - Image publishing to DockerHub
   - Deployment

## 📚 Documentation

API documentation is available through Swagger UI at:
```
http://localhost:8080/swagger-ui.html
```

## 🧪 Testing

Run tests using:
```bash
mvn test
```

## 👥 Contributing

1. Create a feature branch (`git checkout -b feature/amazing-feature`)
2. Commit changes (`git commit -m 'Add amazing feature'`)
3. Push to branch (`git push origin feature/amazing-feature`)
4. Open a Pull Request

