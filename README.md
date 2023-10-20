# Project 
>mangayukiii service

## Intro
>Service for comic web created by yukiii

## Operation

### Health Check

```shell
http://localhost:8080/actuator
http://localhost:8080/actuator/health
```

### Swagger

```shell
http://localhost:8080/swagger-ui/index.html
```

Please follow this sample which is well aligned with Swagger API Annotation
[Swagger API Controller Sample](https://github.com/springdoc/springdoc-openapi-demos/tree/employee/springdoc-openapi-spring-boot-2-webmvc/src/main/java/org/springdoc/demo/app2/api)

### Run (Local)

```shell
make run-local
```

### Run Docker (Local)

```shell
make stack-up
```