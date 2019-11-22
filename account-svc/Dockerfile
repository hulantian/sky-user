FROM openjdk:8-jdk-alpine
MAINTAINER 世纪云合科技有限公司 developer@shijiyunhe.com
VOLUME ["temp"]
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone
ADD target/*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dname=user","-Xmx512m","-Xms512m","-Xmn128m","-XX:SurvivorRatio=8","-XX:MetaspaceSize=128m","-XX:MaxMetaspaceSize=128m","-jar", "app.jar"]
EXPOSE 8080