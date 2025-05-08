# Usa imagen base con Java 21
FROM eclipse-temurin:21-jdk-alpine as builder

# Directorio de trabajo para la app
WORKDIR /app

# Copia el contenido del proyecto
COPY . .

# Da permisos al wrapper de Maven y compila el JAR sin tests
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# ----------- IMAGEN FINAL -------------
FROM eclipse-temurin:21-jdk

# Directorio de trabajo en la imagen final
WORKDIR /app

# Copia el JAR desde la imagen de construcción
COPY --from=builder /app/target/*.jar app.jar

# Expone el puerto (se define en docker-compose)
EXPOSE 8081

# Comando para ejecutar el microservicio
CMD ["java", "-jar", "app.jar"]
