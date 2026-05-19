package cl.duoc.sigefi.vehiculos.dto;

import cl.duoc.sigefi.vehiculos.model.enums.EstadoTramite;
import cl.duoc.sigefi.vehiculos.model.enums.TipoVehiculo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoResponseDTO {

    private Long id;
    private String patente;
    private String marca;
    private String modelo;
    private Integer anio;
    private TipoVehiculo tipoVehiculo;
    private String rutPropietario;
    private String nombrePropietario;
    private String paisOrigen;
    private String paisDestino;
    private EstadoTramite estadoTramite;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaActualizacion;
}
