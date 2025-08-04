# 1. Build aşaması: Maven ile jar üret
FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /app

# Sadece pom dosyasını önce kopyalayıp cache almak daha iyidir:
COPY pom.xml .
RUN mvn dependency:go-offline

# Kaynak kodu kopyala ve paketi oluştur
COPY src ./src
RUN mvn clean package -DskipTests

# 2. Runtime aşaması: Hafif JDK kullan
FROM openjdk:21-jdk-slim

WORKDIR /app

EXPOSE 8080

# Build aşamasındaki jar'ı al. İsim değişken olabileceği için wildcard ve rename yapıyoruz
COPY --from=build /app/target/*-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
