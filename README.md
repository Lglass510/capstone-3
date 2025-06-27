# EasyShop Backend API

Welcome to **EasyShop**, a backend API built using Java and Spring Boot. This project powers the core features of an e-commerce application — everything from listing products and filtering by category to managing shopping carts and handling authentication.

This isn't just a demo — it's structured with real-world design patterns like DAOs and controllers, making it maintainable, testable, and scalable.

---

## 🧠 Purpose

The EasyShop API is designed to serve as the backend for an online shopping platform. It allows frontend applications to:

- Retrieve product listings and categories
- Search for products by color, price range, and category
- Manage shopping carts
- Authenticate users (login)
  
It separates concerns cleanly — the **controllers** handle HTTP requests, the **DAOs** interact with the database, and the **configuration** classes manage database connections.

---

## 🔧 How It Works

### Project Highlights:

- **Spring Boot** serves as the backbone of the application
- **MySQL** is used for persistent data storage
- **JDBC Template + DAO Pattern** keeps data access clean and testable
- Controllers are RESTful and follow a logical route structure
- Flexible search functionality allows filtering by color, price, and category

### Core Functionality Includes:

| Feature                      | Description                                               |
|-----------------------------|-----------------------------------------------------------|
| 🛍️ Product Browsing         | View all products or filter by price, color, category     |
| 🗂️ Categories               | Retrieve and browse products by category                 |
| 🛒 Shopping Cart             | Add, remove, and list cart items for a user              |
| 🔐 Authentication           | Secure login endpoint for user verification              |

---

## 🗂️ Project Structure

```bash
src/main/java/org/yearup/
│
├── EasyshopApplication.java        # Main entry point
├── configurations/                 # Database configuration
├── controllers/                    # REST API endpoints (Products, Cart, Auth)
├── data/                           # DAO interfaces and JDBC implementations
├── models/                         # Domain models (e.g., Product, Category)
└── services/                       # Business logic layer (if used)
