# vehiculos-service

Microservicio SIGEFI para RF02 Registro Vehicular.

## Requisitos

- Java 17
- Maven
- MySQL
- Eureka Server en `http://localhost:8761/eureka/` si se desea registrar el servicio

## Base de datos

Crear la base de datos antes de ejecutar:

```sql
CREATE DATABASE IF NOT EXISTS sigefi_vehiculos_db;
```

El script `src/main/resources/schema.sql` crea la tabla `vehiculos` e índices iniciales.

## Ejecutar

```bash
mvn clean install
mvn spring-boot:run
```

## Endpoints principales

- `POST /api/v1/vehiculos`
- `GET /api/v1/vehiculos`
- `GET /api/v1/vehiculos/{id}`
- `GET /api/v1/vehiculos/propietario/{rutPropietario}`
- `GET /api/v1/vehiculos/estado/{estadoTramite}`
- `PUT /api/v1/vehiculos/{id}`
- `PATCH /api/v1/vehiculos/{id}/estado`
- `DELETE /api/v1/vehiculos/{id}`

## Ejemplo POST

```json
{
  "patente": "ABCD12",
  "marca": "Toyota",
  "modelo": "Hilux",
  "anio": 2023,
  "tipoVehiculo": "CAMIONETA",
  "rutPropietario": "12345678-9",
  "nombrePropietario": "Juan Perez",
  "paisOrigen": "Chile",
  "paisDestino": "Argentina"
}
```
