# Shop-Billing-System-using-Java-and-MySQL
Shop Billing System developed in Java (NetBeans) with MySQL database (WAMP Server)


---

## 🚀 Features
- *Book Management*
  - Add new books
  - Search and update book details
- *Member Management*
  - Register new members
  - View and update member details
- *Borrow & Return*
  - Issue books to members
  - Return and update status
- *Inquiries & Reports*
  - Book inquiry
  - Member inquiry
  - Daily reports
  - Inquiry tables
- *Settings & User Control*
  - Logout
  - Admin settings

---

## 🖼 System Dashboard
 <img width="1041" height="502" alt="Main Menu" src="https://github.com/user-attachments/assets/283792b5-5d46-496d-9285-41e580f1ff02" />


---

## 🛠 Technologies Used
- *Java (NetBeans IDE)*
- *MySQL (WAMP Server)*
- *JDBC* for database connectivity
- *Swing / JavaFX* for user interface (depending on your setup)

---


## 📥 Installation & Setup
1. **Clone the repository**
   bash
   git clone https://github.com/hanshanaweerakoon/Library-Management-System-Java-MySQL-.git

2. **Open in NetBeans**

   * Go to File → Open Project → Select the project folder.

3. **Database Setup**

   * Start WAMP Server.
   * Open `http://localhost/phpmyadmin`.
   * Create a new database (e.g., `library_db`).
   * Import `database/library.sql`.

4. **Configure Database Connection**
   Update your DB connection settings in the Java file:

```java
   String url = "jdbc:mysql://localhost/library_db";
   String user = root;
   String pass = "";
```
5. **Run the Project**

   * Press `F6` in NetBeans.

---

## 📸 More Screenshots


<img width="979" height="545" alt="Login" src="https://github.com/user-attachments/assets/305df5da-3565-4a81-9f32-90c051f83fdb" />

---

<img width="1235" height="665" alt="Inquiry" src="https://github.com/user-attachments/assets/d5bd19b9-23a5-4b91-aad5-9b5e4765516e" />

---

<img width="1123" height="661" alt="Daily report" src="https://github.com/user-attachments/assets/6997dc72-2282-4d2e-b3af-93af886a049f" />

---

<img width="1183" height="621" alt="Books" src="https://github.com/user-attachments/assets/17c23861-702f-45b2-ae58-05de487087bb" />

---

<img width="1296" height="670" alt="Members" src="https://github.com/user-attachments/assets/fc978ee9-8500-4ec9-a49e-c911598fe120" />
