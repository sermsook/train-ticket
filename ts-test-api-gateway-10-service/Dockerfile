FROM java:8-jre

RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

ADD ./target/ts-test-api-gateway-10-service-1.0.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/ts-test-api-gateway-10-service-1.0.jar"]

EXPOSE 20009