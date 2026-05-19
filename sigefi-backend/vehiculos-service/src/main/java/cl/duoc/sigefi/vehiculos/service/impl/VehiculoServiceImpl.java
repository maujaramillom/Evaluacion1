package cl.duoc.sigefi.vehiculos.service.impl;

import cl.duoc.sigefi.vehiculos.dto.EstadoRequestDTO;
import cl.duoc.sigefi.vehiculos.dto.VehiculoRequestDTO;
import cl.duoc.sigefi.vehiculos.dto.VehiculoResponseDTO;
import cl.duoc.sigefi.vehiculos.exception.BusinessException;
import cl.duoc.sigefi.vehiculos.exception.DuplicateResourceException;
import cl.duoc.sigefi.vehiculos.exception.ResourceNotFoundException;
import cl.duoc.sigefi.vehiculos.mapper.VehiculoMapper;
import cl.duoc.sigefi.vehiculos.model.entity.Vehiculo;
import cl.duoc.sigefi.vehiculos.model.enums.EstadoTramite;
import cl.duoc.sigefi.vehiculos.repository.VehiculoRepository;
import cl.duoc.sigefi.vehiculos.service.VehiculoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class VehiculoServiceImpl implements VehiculoService {

    private final VehiculoRepository vehiculoRepository;
    private final VehiculoMapper vehiculoMapper;

    @Override
    public VehiculoResponseDTO crear(VehiculoRequestDTO request) {
        log.info("Iniciando creación de vehículo, patente={}", request.getPatente());

        validarAnio(request.getAnio());
        request.setPatente(normalizarPatente(request.getPatente()));

        if (vehiculoRepository.existsByPatenteIgnoreCase(request.getPatente())) {
            log.warn("Intento de creación con patente duplicada={}", request.getPatente());
            throw new DuplicateResourceException("Ya existe un vehículo registrado con la patente " + request.getPatente());
        }

        Vehiculo vehiculo = vehiculoMapper.toEntity(request);
        Vehiculo guardado = vehiculoRepository.save(vehiculo);

        log.info("Vehículo creado correctamente, id={}, patente={}", guardado.getId(), guardado.getPatente());
        return vehiculoMapper.toResponse(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehiculoResponseDTO> listar() {
        log.info("Listando todos los vehículos");
        return vehiculoRepository.findAll()
                .stream()
                .map(vehiculoMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public VehiculoResponseDTO buscarPorId(Long id) {
        log.info("Buscando vehículo por id={}", id);
        Vehiculo vehiculo = obtenerVehiculoPorId(id);
        return vehiculoMapper.toResponse(vehiculo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehiculoResponseDTO> buscarPorRutPropietario(String rutPropietario) {
        log.info("Buscando vehículos por rutPropietario={}", rutPropietario);
        return vehiculoRepository.findByRutPropietarioIgnoreCase(rutPropietario)
                .stream()
                .map(vehiculoMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehiculoResponseDTO> buscarPorEstado(EstadoTramite estadoTramite) {
        log.info("Buscando vehículos por estadoTramite={}", estadoTramite);
        return vehiculoRepository.findByEstadoTramite(estadoTramite)
                .stream()
                .map(vehiculoMapper::toResponse)
                .toList();
    }

    @Override
    public VehiculoResponseDTO actualizar(Long id, VehiculoRequestDTO request) {
        log.info("Iniciando actualización de vehículo, id={}", id);

        validarAnio(request.getAnio());
        request.setPatente(normalizarPatente(request.getPatente()));

        Vehiculo vehiculo = obtenerVehiculoPorId(id);

        if (vehiculoRepository.existsByPatenteIgnoreCaseAndIdNot(request.getPatente(), id)) {
            log.warn("Intento de actualización con patente duplicada={}, id={}", request.getPatente(), id);
            throw new DuplicateResourceException("Ya existe otro vehículo con la patente " + request.getPatente());
        }

        vehiculoMapper.updateEntity(vehiculo, request);
        Vehiculo actualizado = vehiculoRepository.save(vehiculo);

        log.info("Vehículo actualizado correctamente, id={}, patente={}", actualizado.getId(), actualizado.getPatente());
        return vehiculoMapper.toResponse(actualizado);
    }

    @Override
    public VehiculoResponseDTO cambiarEstado(Long id, EstadoRequestDTO request) {
        log.info("Cambiando estado de vehículo, id={}, nuevoEstado={}", id, request.getEstadoTramite());

        Vehiculo vehiculo = obtenerVehiculoPorId(id);
        validarTransicionEstado(vehiculo.getEstadoTramite(), request.getEstadoTramite());

        vehiculo.setEstadoTramite(request.getEstadoTramite());
        Vehiculo actualizado = vehiculoRepository.save(vehiculo);

        log.info("Estado de vehículo actualizado, id={}, estado={}", actualizado.getId(), actualizado.getEstadoTramite());
        return vehiculoMapper.toResponse(actualizado);
    }

    @Override
    public void eliminar(Long id) {
        log.info("Iniciando eliminación de vehículo, id={}", id);
        Vehiculo vehiculo = obtenerVehiculoPorId(id);
        vehiculoRepository.delete(vehiculo);
        log.info("Vehículo eliminado correctamente, id={}", id);
    }

    private Vehiculo obtenerVehiculoPorId(Long id) {
        return vehiculoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Vehículo no encontrado, id={}", id);
                    return new ResourceNotFoundException("No existe vehículo con id " + id);
                });
    }

    private void validarAnio(Integer anio) {
        int anioMaximoPermitido = Year.now().getValue() + 1;
        if (anio > anioMaximoPermitido) {
            log.warn("Año de vehículo inválido={}, máximo permitido={}", anio, anioMaximoPermitido);
            throw new BusinessException("El año del vehículo no puede ser mayor a " + anioMaximoPermitido);
        }
    }

    private void validarTransicionEstado(EstadoTramite estadoActual, EstadoTramite nuevoEstado) {
        if (estadoActual == EstadoTramite.ANULADO) {
            throw new BusinessException("Un trámite anulado no puede cambiar de estado");
        }

        if (estadoActual == EstadoTramite.APROBADO && nuevoEstado == EstadoTramite.BORRADOR) {
            throw new BusinessException("Un trámite aprobado no puede volver a estado BORRADOR");
        }

        if (estadoActual == EstadoTramite.RECHAZADO && nuevoEstado == EstadoTramite.BORRADOR) {
            throw new BusinessException("Un trámite rechazado no puede volver a estado BORRADOR");
        }
    }

    private String normalizarPatente(String patente) {
        return patente.trim().toUpperCase(Locale.ROOT);
    }
}
