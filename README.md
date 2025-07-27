
# 🏥 MediMate - Your Digital Hospital Management System

## 📈 Project Overview

**MediMate** is a Spring Boot-based Hospital Management System designed to streamline medical workflows in hospitals and clinics. It enables efficient management of patients, doctors, appointments, billing, staff, pharmacy, and diagnostics, all within a secure, modular, and scalable backend architecture.

⚙️ Built for educational, clinical, and administrative use, MediMate supports role-based access and RESTful APIs to power modern healthcare platforms.

---

## 🔧 Features

- 🧑‍⚕️ **Patient & Doctor Management**: Registration, profiles, and assignment handling.
- 📅 **Appointment Booking**: Doctor-wise scheduling and tracking.
- 💊 **Pharmacy Module**: Medicine inventory, sales, and prescription linking.
- 🧾 **Billing System**: Dynamic generation of bills and invoices with itemized services.
- 🧪 **Laboratory**: Test ordering and reporting.
- 🧑‍💼 **Staff Management**: HR functionalities for hospital staff.
- 📊 **Admin Dashboard**: Visual stats and reports for appointments, revenue, and system health.
- 🔐 **Security**: JWT-based authentication with Spring Security.
- 🧰 **Microservices Ready**: Modular structure with Eureka Service Discovery and API Gateway.
- 📑 **PDF Generation**: Patient reports, bills, and appointment summaries.
- 📬 **Email & SMS Notifications**: Alerts for appointments, prescriptions, and bills.

---

## 🗂 Code Structure

```
HMS_PRJCT
├── src
│   ├── main
│   │   ├── java/com/medimate
│   │   │   ├── controller       # REST Controllers for all modules
│   │   │   ├── entity           # JPA Entities
│   │   │   ├── repository       # Spring Data JPA Repositories
│   │   │   ├── service          # Business Logic
│   │   │   ├── config           # JWT, Security, CORS, etc.
│   │   │   └── exception        # Global Exception Handling
│   │   └── resources
│   │       ├── application.properties
│   │       └── static/templates # (if applicable)
├── pom.xml
└── README.md
```

> Each module is decoupled for scalability and future conversion to full microservices.

---

## ⛏ Installation

### Prerequisites

- Java 8+
- Maven
- MySQL (or compatible RDBMS)
- Postman (for API testing)

### Steps

1. **Clone the repository:**

```bash
git clone https://github.com/aashut0xhkr/HMS_PRJCT.git
cd HMS_PRJCT
```

2. **Configure database:**

Update `application.properties` with your DB credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/medimate
spring.datasource.username=root
spring.datasource.password=your_password
```

3. **Run the application:**

```bash
mvn spring-boot:run
```

4. **Access APIs:**

```
http://localhost:8080/api/v1/patients
http://localhost:8080/api/v1/doctors
...
```

---

## ▶️ Usage

### API Endpoints (Sample)

| Endpoint | Method | Description |
|---------|--------|-------------|
| `/api/v1/patients` | GET/POST | Register or fetch patients |
| `/api/v1/doctors` | GET/POST | Manage doctor data |
| `/api/v1/appointments` | POST | Book an appointment |
| `/api/v1/bills` | GET/POST | Generate and retrieve bills |

> Postman collection coming soon!

---

## 🔐 Authentication

- JWT Token-based security
- Login Endpoint: `/api/auth/login`
- Secure endpoints require `Authorization: Bearer <token>`

---

## 💡 Development Notes

- DTOs and validation used for clean API design.
- Global exception handler via `@ControllerAdvice`.
- Service-layer abstraction ensures separation of concerns.
- Ready for Docker and CI/CD setup.

---

## 🛠️ Contributing

We welcome contributions to improve MediMate! 🛠️

1. Fork the repo
2. Create your branch: `git checkout -b feature-name`
3. Commit changes: `git commit -m "Add feature"`
4. Push: `git push origin feature-name`
5. Submit a Pull Request

---

---

## ❤️ Contributors

Thanks to all contributors who make this project possible!

[![Contributors](https://contrib.rocks/image?repo=aashut0xhkr/HMS_PRJCT)](https://github.com/aashut0xhkr/HMS_PRJCT/graphs/contributors)

<p align="center">
  <img src="https://user-images.githubusercontent.com/74038190/212284100-561aa473-3905-4a80-b561-0d28506553ee.gif" width="600">
</p>
