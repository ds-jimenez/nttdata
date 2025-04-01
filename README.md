# NTTData Microservices Architecture

Este proyecto contiene dos microservicios: **Client Service** y **Account Service**, comunicados vía **RabbitMQ**, y conectados a una base de datos **MySQL** con datos de prueba precargados.

---

## 📦 Contenido

- `client-microservice/`: Gestión de clientes y personas
- `account-microservice/`: Gestión de cuentas, transacciones y reportes
- `init.sql`: Script que crea la base de datos `nttdata`, las tablas, y 5 registros de prueba por tabla
- `docker-compose.yml`: Orquestación de contenedores
- `Dockerfile`: Para cada microservicio, empaqueta la app

---

## 🚀 Cómo levantar el proyecto

1. Asegúrate de tener Docker instalado
2. Ubícate en la carpeta raíz del proyecto
3. Ejecuta:

```bash
docker-compose up --build
