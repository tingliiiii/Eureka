# 使用官方 Java 運行環境作為基礎鏡像（我要使用 Java 17 版本）
# 容器化後各自獨立，可各自使用不同版本
FROM openjdk:17-jdk-slim

# 設定容器內部工作目錄
# 容器就像電腦，指定要放在硬碟的位置
WORKDIR /app

# 將應用的 jar 文件從構建上下文，複製到容器內
# 將本機的 jar 檔複製到容器內
COPY target/Hello-Service-8086-0.0.1-SNAPSHOT.jar /app/hello-service.jar

# 容器啟動時執行的命令，啟動 Spring Boot 應用
# 如同在終端機執行的命令：java -jar target/Hello-Service-8086-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "/app/hello-service.jar"]

# 暴露 8086 端口，供外部訪問
EXPOSE 8086