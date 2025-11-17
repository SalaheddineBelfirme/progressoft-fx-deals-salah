FROM maven:3.9.9-eclipse-temurin-21-alpine AS maven
WORKDIR /app

# نسخ pom.xml أولاً (لتحسين caching)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# نسخ الكود وبناء التطبيق
COPY src ./src
RUN mvn clean package -DskipTests

# مرحلة التشغيل النهائية
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=maven /app/target/fx-deal-salah-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8081
CMD ["java", "-jar", "app.jar"]