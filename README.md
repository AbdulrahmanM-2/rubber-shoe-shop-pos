# 📦 Timeless Shoes POS & Inventory System

[![GitHub Actions](https://img.shields.io/github/actions/workflow/status/<USERNAME>/<REPO>/ci-cd.yml?branch=main?style=flat-square)]([https://github.com/<USERNAME>/<REPO>](https://github.com/AbdulrahmanM-2/rubber-shoe-shop-pos)/actions)  
[![License](https://img.shields.io/github/license/AbdulrahmanM-2/rubber-shoe-shop-pos)](LICENSE)  
[![Docker](https://img.shields.io/badge/docker-ready-blue?style=flat-square)](https://www.docker.com/)  

---

## 🖥️ Dashboard Preview

![Dashboard](https://github.com/AbdulrahmanM-2/rubber-shoe-shop-pos/blob/main/src/main/resources/Dashboard.png)  
*POS Dashboard – quick overview of sales, stock, and reports.*

---

## 🛒 Order Management

![Orders](https://github.com/AbdulrahmanM-2/rubber-shoe-shop-pos/blob/main/src/main/resources/Orders.png)  
*Easily create orders with multiple variants and payment options.*

---

## 💰 Sales & Reports

![Sales Report](https://github.com/AbdulrahmanM-2/rubber-shoe-shop-pos/blob/main/src/main/resources/Sales%20Report.png)  
*View daily sales, profits, and low-stock alerts in one place.*

---

## ⚡ Animated Workflow

![Create Order GIF](https://github.com/AbdulrahmanM-2/rubber-shoe-shop-pos/blob/main/src/main/resources/Animated%20Workflow.gif)  
*Watch how a cashier creates an order and processes payment.*

---

## 🧩 Features

- Multi-role user management (`OWNER`, `MANAGER`, `CASHIER`, `STOREKEEPER`)  
- Product & variant stock tracking (size, color)  
- Orders, payments, and stock movement management  
- Reports: Daily Sales, Profit, Low Stock, Payment Summary  
- Multi-language support: English + Swahili  
- Interactive API docs with **Swagger/OpenAPI**  
- Fully containerized: **Docker + Docker Compose**  

---

## 🏗️ Quick Start

```bash
git clone https://github.com/<USERNAME>/<REPO>.git
cd timeless-shoes-shop
git checkout feature/backend-docker

# Environment variables
export APP_ADMIN_USERNAME="owner"
export APP_ADMIN_PASSWORD="StrongPassword123"

docker-compose up --build
