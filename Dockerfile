# 1. aşama: build (Java 21 ile)
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app

# Önce pom.xml ile cache katmanı
COPY pom.xml .
RUN mvn -B dependency:go-offline

# Kaynakları al ve paketle
COPY src ./src
RUN mvn -B clean package -DskipTests

# 2. aşama: runtime (daha küçük, sadece JRE)
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
EXPOSE 8080

# Build aşamasından çıkan jar'ı al (isim değişebilir, wildcard ile)
COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
