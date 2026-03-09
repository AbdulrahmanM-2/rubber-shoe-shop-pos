# 📦 Timeless Shoes POS & Inventory System

[![GitHub Actions](https://img.shields.io/github/actions/workflow/status/AbdulrahmanM-2/rubber-shoe-shop-pos/ci-cd.yml?branch=main&style=flat-square)](https://github.com/AbdulrahmanM-2/rubber-shoe-shop-pos)
[![License](https://img.shields.io/github/license/AbdulrahmanM-2/rubber-shoe-shop-pos)](LICENSE)
[![Docker](https://img.shields.io/badge/docker-ready-blue?style=flat-square)](https://www.docker.com/)

---

## 🖥️ Dashboard Preview

![Dashboard](https://raw.githubusercontent.com/AbdulrahmanM-2/rubber-shoe-shop-pos/main/rubber_shoe_pos/backend/src/main/resources/Dashboard.png)
*POS Dashboard – quick overview of sales, stock, and reports.*

---

## 🛒 Order Management

![Orders](https://raw.githubusercontent.com/AbdulrahmanM-2/rubber-shoe-shop-pos/main/rubber_shoe_pos/backend/src/main/resources/Orders.png)
*Easily create orders with multiple variants and payment options.*

---

## 💰 Sales & Reports

![Sales Report](https://raw.githubusercontent.com/AbdulrahmanM-2/rubber-shoe-shop-pos/main/rubber_shoe_pos/backend/src/main/resources/Sales%20Report.png)
*View daily sales, profits, and low-stock alerts in one place.*

---

## ⚡ Animated Workflow

![Create Order GIF](https://raw.githubusercontent.com/AbdulrahmanM-2/rubber-shoe-shop-pos/main/rubber_shoe_pos/backend/src/main/resources/Animated%20Workflow.gif)
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
git clone https://github.com/AbdulrahmanM-2/rubber-shoe-shop-pos.git
cd rubber-shoe-shop-pos/rubber_shoe_pos

# Environment variables
export APP_ADMIN_USERNAME="owner"
export APP_ADMIN_PASSWORD="StrongPassword123"

docker-compose up --build
```

---

## 🔑 Environment Variables

| Variable | Description | Example |
|---|---|---|
| `DB_HOST` | PostgreSQL host | `localhost` |
| `DB_PORT` | PostgreSQL port | `5432` |
| `DB_NAME` | Database name | `timeless_shoes` |
| `DB_USER` | Database user | `postgres` |
| `DB_PASSWORD` | Database password | `secret` |
| `JWT_SECRET` | JWT signing secret (min 32 chars) | `your-secret-key-here` |
| `SPRING_PROFILES_ACTIVE` | Active Spring profile | `prod` |

---

## 📁 Project Structure

```
rubber_shoe_pos/
├── backend/                  # Spring Boot API
│   ├── src/main/java/com/timeless/shoes/
│   │   ├── auth/             # JWT authentication
│   │   ├── config/           # Security & web config
│   │   ├── customers/        # Customer management
│   │   ├── dashboard/        # Dashboard metrics & WebSocket
│   │   ├── entity/           # JPA entities (User, Order, Shift)
│   │   ├── product/          # Products & variants
│   │   ├── report/           # Sales & stock reports
│   │   ├── sales/            # Sales transactions
│   │   ├── service/          # Business logic services
│   │   └── stock/            # Stock movements
│   └── src/main/resources/
│       ├── application.yml
│       └── application-prod.yml
├── frontend/                 # React 18 frontend
│   ├── src/
│   └── package.json
├── Dockerfile                # Single-service build (frontend + backend)
└── docker-compose.yml
```

---

## 🌐 API Documentation

Once running, Swagger UI is available at:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🚀 Deploying to Railway

1. Connect your GitHub repo to Railway
2. Set **Root Directory** to `rubber_shoe_pos`
3. Add a PostgreSQL plugin
4. Set the environment variables listed above
5. Railway will auto-deploy on every push to `main`
