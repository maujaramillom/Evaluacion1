package cl.duoc.sigefi.vehiculos.model.entity;

import cl.duoc.sigefi.vehiculos.model.enums.EstadoTramite;
import cl.duoc.sigefi.vehiculos.model.enums.TipoVehiculo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "vehiculos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String patente;

    @Column(nullable = false, length = 50)
    private String marca;

    @Column(nullable = false, length = 50)
    private String modelo;

    @Column(nullable = false)
    private Integer anio;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_vehiculo", nullable = false, length = 20)
    private TipoVehiculo tipoVehiculo;

    @Column(name = "rut_propietario", nullable = false, length = 12)
    private String rutPropietario;

    @Column(name = "nombre_propietario", nullable = false, length = 120)
    private String nombrePropietario;

    @Column(name = "pais_origen", nullable = false, length = 60)
    private String paisOrigen;

    @Column(name = "pais_destino", nullable = false, length = 60)
    private String paisDestino;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_tramite", nullable = false, length = 30)
    private EstadoTramite estadoTramite;

    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @PrePersist
    public void prePersist() {
        this.fechaRegistro = LocalDateTime.now();
        if (this.estadoTramite == null) {
            this.estadoTramite = EstadoTramite.BORRADOR;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }
}
