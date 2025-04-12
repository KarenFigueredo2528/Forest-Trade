# 🌲 ForestTrade - Plataforma de Simulación de Inversiones

ForestTrade es una aplicación web de simulación de inversiones en acciones, construida con **Spring Boot** en el backend y **React** en el frontend. El sistema está conectado con una base de datos **MySQL** y se encuentra listo para ser integrado con **Alpaca Broker API** para crear y consultar órdenes bursátiles reales o simuladas.

---

## 📁 Estructura del Proyecto

foresttrade/ ├── backend/ → API REST con Spring Boot ├── frontend/ → Interfaz de usuario con React └── README.md


---

## 🚀 ¿Cómo ejecutar el proyecto?

### 🔧 Requisitos Previos

- Java 17+
- Node.js 18+
- MySQL
- Maven
- Docker (opcional, para levantar todo junto)
- Postman (opcional, para probar endpoints)

---

### 🖥️ 1. Backend (Spring Boot)

**Ruta:** `./backend`

``bash
# Entrar a la carpeta del backend
cd backend

# Ejecutar desde IntelliJ o usando Maven
./mvnw spring-boot:run

Esto inicia el backend en:
http://localhost:8080/api/usuarios

🔌 Endpoints disponibles:
GET /api/usuarios → Listar usuarios

POST /api/usuarios → Crear nuevo usuario

GET /api/usuarios/buscar?correo=... → Buscar usuario por correo

# Entrar a la carpeta del frontend
cd frontend

# Instalar dependencias
npm install

# Ejecutar app
npm run dev

