CREATE TABLE IF NOT EXISTS vehiculos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    patente VARCHAR(10) NOT NULL,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    anio INT NOT NULL,
    tipo_vehiculo VARCHAR(20) NOT NULL,
    rut_propietario VARCHAR(12) NOT NULL,
    nombre_propietario VARCHAR(120) NOT NULL,
    pais_origen VARCHAR(60) NOT NULL,
    pais_destino VARCHAR(60) NOT NULL,
    estado_tramite VARCHAR(30) NOT NULL DEFAULT 'BORRADOR',
    fecha_registro DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion DATETIME NULL,
    CONSTRAINT pk_vehiculos PRIMARY KEY (id),
    CONSTRAINT uk_vehiculos_patente UNIQUE (patente),
    CONSTRAINT chk_vehiculos_anio CHECK (anio BETWEEN 1900 AND 2100)
);

CREATE INDEX idx_vehiculos_rut_propietario ON vehiculos (rut_propietario);
CREATE INDEX idx_vehiculos_estado_tramite ON vehiculos (estado_tramite);
