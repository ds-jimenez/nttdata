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
```

## Tecnologías
1. Java 21
2. Spring Boot
3. RabbitMQ
4. ModelMapper
5. Swagger OpenAPI
6. Maven
7. MySql
8. Docker

<<<<<<< HEAD
##  Arquitectura Limpia
=======
## Arquitectura Limpia
>>>>>>> 27750ee67cba2ecfccab7eb3a5cecda8ce7397c6

La estructura del proyecto se organiza en capas claramente definidas, siguiendo los principios de Clean Architecture:

### client-microservice

```plaintext
src/main/java/com/nttdata/microservice/client/
├── api/                  # Capa de entrada: controladores REST
│   └── controller/
├── application/          # Casos de uso y DTOs
│   ├── dto/
│   └── usecase/          # Interfaces de servicios de aplicación
├── domain/               # Modelo de negocio y repositorios (puertos)
│   ├── model/
│   └── repository/
├── infrastructure/       # Adaptadores: base de datos, mensajería, etc.
│   ├── publisher/        # Publicación de eventos (RabbitMQ)
│   └── service/          # Implementaciones concretas de casos de uso
├── common/               # Clases compartidas (ej. ApiResponse)
├── config/               # Configuraciones de Spring Boot
└── ClientApplication.java  # Clase principal

```
### account-microservice
```plaintext
src/main/java/com/nttdata/microservice/account/
├── api/                  
│   ├── controller/        # Controladores REST (Account, Transaction, Report)
│   └── dto/               # DTOs de entrada/salida para la API
├── application/
│   ├── dto/               # DTOs internos de la lógica de aplicación
│   └── usecase/           # Interfaces de servicios de aplicación (casos de uso)
├── domain/
│   ├── model/             # Entidades del dominio: Account, Transaction, Client, Person
│   └── repository/        # Interfaces de persistencia (puertos de salida)
├── infrastructure/
│   ├── service/           # Implementaciones concretas de los casos de uso
│   └── event/
│       └── consumer/      # Componentes que reciben eventos (RabbitMQ)
├── common/               
│   └── FormResponse.java  # Wrapper de respuesta estándar
├── config/               
│   ├── RabbitMQConfig.java
│   ├── ModelMapperConfig.java
│   └── SwaggerConfig.java
└── AccountApplication.java  # Clase principal del microservicio
