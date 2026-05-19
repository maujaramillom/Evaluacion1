package cl.duoc.sigefi.vehiculos.service;

import cl.duoc.sigefi.vehiculos.dto.EstadoRequestDTO;
import cl.duoc.sigefi.vehiculos.dto.VehiculoRequestDTO;
import cl.duoc.sigefi.vehiculos.dto.VehiculoResponseDTO;
import cl.duoc.sigefi.vehiculos.model.enums.EstadoTramite;

import java.util.List;

public interface VehiculoService {

    VehiculoResponseDTO crear(VehiculoRequestDTO request);

    List<VehiculoResponseDTO> listar();

    VehiculoResponseDTO buscarPorId(Long id);

    List<VehiculoResponseDTO> buscarPorRutPropietario(String rutPropietario);

    List<VehiculoResponseDTO> buscarPorEstado(EstadoTramite estadoTramite);

    VehiculoResponseDTO actualizar(Long id, VehiculoRequestDTO request);

    VehiculoResponseDTO cambiarEstado(Long id, EstadoRequestDTO request);

    void eliminar(Long id);
}
