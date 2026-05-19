package cl.duoc.sigefi.vehiculos.mapper;

import cl.duoc.sigefi.vehiculos.dto.VehiculoRequestDTO;
import cl.duoc.sigefi.vehiculos.dto.VehiculoResponseDTO;
import cl.duoc.sigefi.vehiculos.model.entity.Vehiculo;
import cl.duoc.sigefi.vehiculos.model.enums.EstadoTramite;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class VehiculoMapper {

    public Vehiculo toEntity(VehiculoRequestDTO dto) {
        log.debug("Mapeando VehiculoRequestDTO a entidad Vehiculo, patente={}", dto.getPatente());
        return Vehiculo.builder()
                .patente(dto.getPatente())
                .marca(dto.getMarca())
                .modelo(dto.getModelo())
                .anio(dto.getAnio())
                .tipoVehiculo(dto.getTipoVehiculo())
                .rutPropietario(dto.getRutPropietario())
                .nombrePropietario(dto.getNombrePropietario())
                .paisOrigen(dto.getPaisOrigen())
                .paisDestino(dto.getPaisDestino())
                .estadoTramite(EstadoTramite.BORRADOR)
                .build();
    }

    public VehiculoResponseDTO toResponse(Vehiculo vehiculo) {
        log.debug("Mapeando entidad Vehiculo a VehiculoResponseDTO, id={}", vehiculo.getId());
        return VehiculoResponseDTO.builder()
                .id(vehiculo.getId())
                .patente(vehiculo.getPatente())
                .marca(vehiculo.getMarca())
                .modelo(vehiculo.getModelo())
                .anio(vehiculo.getAnio())
                .tipoVehiculo(vehiculo.getTipoVehiculo())
                .rutPropietario(vehiculo.getRutPropietario())
                .nombrePropietario(vehiculo.getNombrePropietario())
                .paisOrigen(vehiculo.getPaisOrigen())
                .paisDestino(vehiculo.getPaisDestino())
                .estadoTramite(vehiculo.getEstadoTramite())
                .fechaRegistro(vehiculo.getFechaRegistro())
                .fechaActualizacion(vehiculo.getFechaActualizacion())
                .build();
    }

    public void updateEntity(Vehiculo vehiculo, VehiculoRequestDTO dto) {
        log.debug("Actualizando entidad Vehiculo desde DTO, id={}, patente={}", vehiculo.getId(), dto.getPatente());
        vehiculo.setPatente(dto.getPatente());
        vehiculo.setMarca(dto.getMarca());
        vehiculo.setModelo(dto.getModelo());
        vehiculo.setAnio(dto.getAnio());
        vehiculo.setTipoVehiculo(dto.getTipoVehiculo());
        vehiculo.setRutPropietario(dto.getRutPropietario());
        vehiculo.setNombrePropietario(dto.getNombrePropietario());
        vehiculo.setPaisOrigen(dto.getPaisOrigen());
        vehiculo.setPaisDestino(dto.getPaisDestino());
    }
}
