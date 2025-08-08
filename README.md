# Shop-Billing-System
The system allows shop owners to:
- Generate sales bills and invoices
- Manage stock and inventory
- Calculate prices and transactions
- Print receipts and reports


---

## 🚀 Features
1. Sales Billing
   * Add items with Unit Price, Quantity, and Total Price
   * Print bills for customers
   * Clear All to reset the form
   * Calculator for manual price adjustments

2. Stock Management
   * Add new stock items
   * Update existing stock (Qly = Quantity)
   * View stock details (Code, Name, Unit Price)

3. Reports & Transactions
   * Generate sales reports
   * View transaction history
   * Inquiry for checking product details

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
   git clone https://github.com/hanshanaweerakoon/Shop-Billing-System-using-Java-and-MySQL.git

2. **Open in NetBeans**

   * Go to File → Open Project → Select the project folder.

3. **Database Setup**

   * Start WAMP Server.
   * Open `http://localhost/phpmyadmin`.
   * Create a new database (e.g., `billing_db`).
   * Import `database/shopbillingsystem.sql`.

4. **Configure Database Connection**
   Update your DB connection settings in the Java file:

```java
   String url = "jdbc:mysql://localhost/billing_db";
   String user = root;
   String pass = "";
```
5. **Run the Project**

   * Press `F6` in NetBeans.

---

## 📸 UI Screenshots
<img width="376" height="483" alt="login" src="https://github.com/user-attachments/assets/ab842d19-8344-4746-8ad6-fc00e7b55406" />

---

<img width="1234" height="700" alt="bill" src="https://github.com/user-attachments/assets/6ab40a0a-bc6b-4db0-9dc9-12da03b8c5e8" />

---

<img width="985" height="495" alt="items" src="https://github.com/user-attachments/assets/2bf6aa18-46a6-4cba-ae47-602dc18032ac" />

---

<img width="1069" height="687" alt="accounts" src="https://github.com/user-attachments/assets/61120422-1eea-44a1-8d50-b5a1093ebc77" />

---

<img width="775" height="622" alt="statement" src="https://github.com/user-attachments/assets/cd5d0f4a-6c02-4447-997b-56e5cf0f822b" />
