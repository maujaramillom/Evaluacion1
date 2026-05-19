# Estructura general recomendada para SIGEFI

Esta estructura replica el patrón CSR exigido para cada microservicio:

```text
nombre-service/
  pom.xml
  src/main/java/cl/duoc/sigefi/nombre/
    NombreServiceApplication.java
    client/
    config/
    controller/
    dto/
    exception/
    mapper/
    model/
      entity/
      enums/
    repository/
    service/
      impl/
  src/main/resources/
    application.yml
    schema.sql
```

Microservicios de dominio sugeridos:

1. vehiculos-service
2. menores-service
3. sag-service
4. mascotas-service
5. pdi-service
6. internacional-service
7. qr-service
8. funcionarios-service
9. notificaciones-service
10. reportes-service

Infraestructura adicional sugerida:

- eureka-server
- api-gateway
- config-server, opcional
