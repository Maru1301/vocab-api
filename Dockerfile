# Use an OpenJDK base image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy pom and source files
COPY . .

# Grant execute permission for mvnw
RUN chmod +x mvnw

# Build the app
RUN ./mvnw clean package -DskipTests

# Expose port (Render will auto-detect this)
EXPOSE 8080

# Run the app
CMD sh -c 'java -jar $(find target -name "*SNAPSHOT.jar" | head -n 1)'

