<h1 align="center">FullStack Image Contact List</h1>

<p align="center">
  🔗 <a href="https://contactmanagementlist.netlify.app/contacts" target="_blank">contactmanagementlist.netlify.app</a>
</p>

# Overview
A previously cloud-hosted CRUD contact management system on EC2, built with Spring Boot and React, featuring secure profile image uploads, pagination, and advanced search. It consists of organizing contact details such as name, email, phone, and address. Designed to streamline contact organization for teams, with centralized storage for details like job titles, home addresses, and status tracking.

---

## Features
- **CRUD Operations**: RESTful API endpoints for Create, Read, Update, and Delete actions with input validation.
- **Profile Image Upload**: Secure server-side storage with file type/size validation to prevent malicious uploads.
- **Pagination & Search**: Multi-page navigation and dynamic filtering for efficient large-dataset management.
- **Responsive UI**: Bootstrap and Flexbox-driven design for seamless cross-device compatibility.

---

## Tech Stack
| Category       | Technologies/Tools                                  |  
|----------------|----------------------------------------------------|  
| **Backend**    | Spring Boot 3.3.5, JPA (Hibernate), Lombok, REST API Design                  |  
| **Frontend**   | React, Axios (API Integration), Bootstrap 5                                              |  
| **Database**   | PostgreSQL (ACID Compliance), pgAdmin                                         |  
| **Testing**    | Selenium 4.20.0 (UI Automation), JUnit 5 (Unit Tests), Postman, WebDriverManager 5.7.0   |  
| **Infrastructure**      | AWS EC2 (Deployment), Docker (Containerization)            |  

---

# Installation
### Prerequisites
- JDK 17+, Maven, PostgreSQL, Node.js/npm.
- **Frontend Repo: [React-CRUDContactList](https://github.com/Nofate4you/React-CRUDContactList).**

### Steps
1. **Clone & Configure Backend**:
   ```bash  
   git clone https://github.com/KevinLlano/Java_CRUDContactList.git  
   cd contactlistapi  
   ```  
   Update `application.properties`:
   ```properties  
   spring.datasource.url=jdbc:postgresql://localhost:5432/contactlistdb  
   spring.datasource.username=postgres  
   spring.datasource.password=yourpassword  
   ```  
   Run:
   ```bash  
   mvn spring-boot:run  # Starts backend at http://localhost:8080  
   ```  

2. **Set Up Frontend**:
   ```bash  
   cd contactlistweb  
   npm install && npm start  # Starts frontend at http://localhost:3000  
   ```  

3. **Database Setup**:
   ```sql  
   CREATE DATABASE contactlistdb;  # Run in PostgreSQL  
   ```  

---

# Testing Documentation
### Test Setup
- **Framework**: JUnit 5 (Unit/Integration Tests) + Selenium (End-to-End UI Testing).
- **Browser Automation**:
   - `WebDriverManager` auto-configures browser drivers for Chrome/Firefox.
   - Explicit waits (20-second timeout) for dynamic elements.
- **Test Flow**:
   1. Navigate to `http://localhost:3000/contacts`.
   2. Add a contact via form submission.
   3. Validate contact appears in the list using XPath `//div[contains(., 'John Doe')]`.

### Key Methods
| Method             | Purpose                                  |  
|--------------------|------------------------------------------|  
| `setUp()`          | Initializes WebDriver and waits.         |  
| `testAddContact()` | Tests contact creation and validation.   |  
| `tearDown()`       | Closes the browser post-test.            |  

### Issues & Fixes
| Issue                        | Symptom                                  | Solution                                  |  
|------------------------------|------------------------------------------|-------------------------------------------|  
| **Selenium Version Mismatch**| CDP warnings for Chrome 133.             | Added `selenium-devtools-v133:4.20.0`.    |  
| **Element Locator Mismatch** | `TimeoutException` with XPath `text()`.  | Updated XPath to `contains(., 'text')`.   |  
| **ChromeDriver Setup**       | Manual driver version conflicts.         | Integrated `WebDriverManager` automation. |  

---

## Notes
- **CDP Warnings**: Non-blocking; resolved by aligning Selenium dependencies.
- **Best Practices**: Use explicit waits and `WebDriverManager` for stable tests.

--- 

# Screenshots
| Description          | Image                                                              |  
|----------------------|--------------------------------------------------------------------|  
| Contact List View    | ![Contact List View](https://github.com/user-attachments/assets/405c37a7-d838-4a3d-bdd5-f89da629477c) |  
| Add Contact Form     | ![Add Contact Form](https://github.com/user-attachments/assets/ccde6110-f610-4b70-896a-9df1a7d3fc96) |  
| Profile Image Upload | ![Profile Image Upload](https://github.com/user-attachments/assets/edc5fb0c-c35b-49d6-b9c2-b6b67f70fc4c) |  
| Pagination Example   | ![Pagination Example](https://github.com/user-attachments/assets/664a39f3-aae3-4500-87e7-2361971936a7) |  


---


