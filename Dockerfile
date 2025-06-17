# Use an OpenJDK base image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy pom and source files
COPY . .

# Build the app
RUN ./mvnw clean package -DskipTests

# Expose port (Render will auto-detect this)
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "target/your-app-name.jar"]
