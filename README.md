# SalesProject
Java-based desktop application for managing a shoe store, featuring user registration, login, product inventory display, sales tracking, and ordering. Built with Swing for the GUI and MySQL for the backend. Developed as part of COP 3703: Database Systems.

# Shoe Store Management System

A Java-based desktop application designed to manage the operations of a small shoe store. It supports user registration, login, product inventory display, order submissions, and sales tracking.

## Features

-  **User Registration & Login**  
  Users can register as customers and log in securely using credentials verified via stored procedures.

-  **Product Inventory**  
  Displays all products available in the store, including their names, prices, and IDs.

-  **Sales Total Tracking**  
  Admins can view the total amount of sales made through the application.

-  **Order Submission**  
  Customers can submit product orders, which are logged into the database.

-  **New Product Submission**  
  Admins can add new products to the inventory.

## Technologies Used

- **Java (Swing GUI)**
- **JDBC (Java Database Connectivity)**
- **MySQL**
- **Stored Procedures**

## Database

The backend database contains tables for:
- Users
- Products
- Orders

Stored procedures are used for secure operations such as registration, login, retrieving product data, and calculating sales totals.

## Getting Started

1. Clone this repository:
   ```bash
   git clone https://github.com/yourusername/shoe-store-app.git
   cd shoe-store-app
