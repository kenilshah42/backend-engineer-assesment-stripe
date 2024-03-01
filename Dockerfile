FROM azul/zulu-openjdk:latest# Install TemporalRUN apk add --no-cache temporal# Install GradlewRUN apk add --no-cache gradlew# Set the working directoryWORKDIR /app# Copy the Gradle wrapperCOPY gradlew ./gradlew# Make the Gradle wrapper executableRUN chmod +x ./gradlew# Copy the Temporal applicationCOPY . .# Build the Temporal applicationRUN temporal server start-dev &# Start the Temporal applicationCMD ./gradlew bootRun