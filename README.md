
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
│   └── main
│       ├── java/com/Major/Project
│       │   ├── Appointment       # Appointment booking and scheduling
│       │   ├── Billing           # Billing module (invoices, payments)
│       │   ├── Configuration     # JWT, security, and app configs
│       │   ├── Doctor            # Doctor registration and management
│       │   ├── Inventory         # Medical stock management
│       │   ├── Laboratory        # Lab test ordering and results
│       │   ├── Patient           # Patient profile and records
│       │   ├── Pharmacy          # Medicine and pharmacy-related logic
│       │   ├── Reporting         # Admin dashboards and report generation
│       │   ├── Security          # Spring Security and JWT auth
│       │   └── Staff             # Hospital staff HR module
│       │
│       └── resources
│           ├── application.yml   # Configuration file
│           ├── static            # Static resources (if any)
│           └── templates         # Thymeleaf or other templates
│
├── pom.xml                       # Maven configuration
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

Update `application.yml` with your DB credentials:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/hospitalManagement
    username: root
    password: 8207
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    database-platform: org.hibernate.dialect.MySQLDialect
  security:
    user:
      name: user
      password: user123
```

3. **Run the application:**

```bash
mvn spring-boot:run
```

4. **Access APIs:**

```
http://localhost:8080/HMS/patients
http://localhost:8080/HMS/doctors
...
```

---

## ▶️ Usage




### API Endpoints for Hospital Management System

| Endpoint | Method | Description |
| :--- | :--- | :--- |
| **Appointment Management** | | |
| `/HMS/Appointment` | `GET` / `POST` | Get all appointments or create a new one. |
| `/HMS/Appointment/{id}` | `GET` / `PUT` / `DELETE` | Retrieve, update, or delete an appointment by ID. |
| `/HMS/Appointment/doctor/{doctorId}` | `GET` | Get all appointments for a specific doctor. |
| `/HMS/Appointment/patient/{patientId}`| `GET` | Get all appointments for a specific patient. |
| **Billing Management** | | |
| `/HMS/Bill` | `GET` / `POST` | Get all bills or create a new one. |
| `/HMS/Bill/{id}` | `GET` / `PUT` / `DELETE` | Retrieve, update, or delete a bill by ID. |
| `/HMS/Bill/Patient/{patientId}` | `GET` | Get all bills for a specific patient. |
| **Doctor Management** | | |
| `/HMS/Doctor` | `GET` / `POST` | Get all doctors or add a new one. |
| `/HMS/Doctor/{id}` | `GET` / `PUT` / `DELETE` | Retrieve, update, or delete a doctor by ID. |
| **Inventory Management** | | |
| `/HMS/Inventory` | `GET` / `POST` | Get all inventory items or create a new one. |
| `/HMS/Inventory/{inventoryId}` | `GET` / `PUT` / `DELETE` | Retrieve, update, or delete an item by ID. |
| `/HMS/Inventory/status/{status}` | `GET` | Get inventory items by status. |
| `/HMS/Inventory/category/{category}` | `GET` | Get inventory items by category. |
| **Lab Test Management** | | |
| `/HMS/LabTest` | `GET` / `POST` | Get all lab tests or create a new one. |
| `/HMS/LabTest/{id}` | `GET` / `PUT` / `DELETE` | Retrieve, update, or delete a lab test by ID. |
| `/HMS/LabTest/patient/{patientId}` | `GET` | Get all lab tests for a specific patient. |
| `/HMS/LabTest/status/{status}` | `GET` | Get lab tests by status. |
| **Medicine & Pharmacy Management** | | |
| `/HMS/Medicine` | `GET` / `POST` | Get all medicines or add a new one. |
| `/HMS/Medicine/{id}` | `GET` / `PUT` / `DELETE` | Retrieve, update, or delete a medicine by ID. |
| `/HMS/Medicine/status/{status}` | `GET` | Get medicines by status. |
| `/HMS/Medicine/search/{name}` | `GET` | Search for medicines by name. |
| **Patient Management** | | |
| `/HMS/Patient` | `GET` / `POST` | Get all patients or register a new one. |
| `/HMS/Patient/{id}` | `GET` / `PUT` / `DELETE` | Retrieve, update, or delete a patient by ID. |
| **Staff Management** | | |
| `/HMS/Staff` | `GET` / `POST` | Get all staff members or add a new one. |
| `/HMS/Staff/{id}` | `GET` / `PUT` / `DELETE` | Retrieve, update, or delete a staff member by ID. |
| `/HMS/Staff/department/{department}` | `GET` | Get staff by department. |
| `/HMS/Staff/role/{role}` | `GET` | Get staff by role. |

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
## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## ❤️ Contributors

Thanks to all contributors who make this project possible!  
[![Contributors](https://contrib.rocks/image?repo=aashut0xhkr/HMS_PRJCT&max=100)](https://github.com/aashut0xhkr/HMS_PRJCT/graphs/contributors)

<p align="center">
  <img src="https://user-images.githubusercontent.com/74038190/212284100-561aa473-3905-4a80-b561-0d28506553ee.gif" width="600">
</p>

---

