# Use official Tomcat base image with Java 17 (change if needed)
FROM tomcat:9.0-jdk17-openjdk

# Remove default ROOT webapp
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copy your WAR file to Tomcat's webapps as ROOT.war (makes app root accessible)
COPY target/StudentPortal.war /usr/local/tomcat/webapps/ROOT.war

# Expose port 8080
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
