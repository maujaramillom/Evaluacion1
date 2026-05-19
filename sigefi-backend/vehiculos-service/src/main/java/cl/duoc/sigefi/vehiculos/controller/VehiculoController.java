package cl.duoc.sigefi.vehiculos.controller;

import cl.duoc.sigefi.vehiculos.dto.EstadoRequestDTO;
import cl.duoc.sigefi.vehiculos.dto.VehiculoRequestDTO;
import cl.duoc.sigefi.vehiculos.dto.VehiculoResponseDTO;
import cl.duoc.sigefi.vehiculos.model.enums.EstadoTramite;
import cl.duoc.sigefi.vehiculos.service.VehiculoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehiculos")
@RequiredArgsConstructor
@Slf4j
@Validated
public class VehiculoController {

    private final VehiculoService vehiculoService;

    @PostMapping
    public ResponseEntity<VehiculoResponseDTO> crear(@Valid @RequestBody VehiculoRequestDTO request) {
        log.info("Solicitud REST POST /api/v1/vehiculos, patente={}", request.getPatente());
        VehiculoResponseDTO response = vehiculoService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<VehiculoResponseDTO>> listar() {
        log.info("Solicitud REST GET /api/v1/vehiculos");
        return ResponseEntity.ok(vehiculoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculoResponseDTO> buscarPorId(@PathVariable @Positive Long id) {
        log.info("Solicitud REST GET /api/v1/vehiculos/{}", id);
        return ResponseEntity.ok(vehiculoService.buscarPorId(id));
    }

    @GetMapping("/propietario/{rutPropietario}")
    public ResponseEntity<List<VehiculoResponseDTO>> buscarPorRutPropietario(@PathVariable String rutPropietario) {
        log.info("Solicitud REST GET /api/v1/vehiculos/propietario/{}", rutPropietario);
        return ResponseEntity.ok(vehiculoService.buscarPorRutPropietario(rutPropietario));
    }

    @GetMapping("/estado/{estadoTramite}")
    public ResponseEntity<List<VehiculoResponseDTO>> buscarPorEstado(@PathVariable EstadoTramite estadoTramite) {
        log.info("Solicitud REST GET /api/v1/vehiculos/estado/{}", estadoTramite);
        return ResponseEntity.ok(vehiculoService.buscarPorEstado(estadoTramite));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehiculoResponseDTO> actualizar(
            @PathVariable @Positive Long id,
            @Valid @RequestBody VehiculoRequestDTO request
    ) {
        log.info("Solicitud REST PUT /api/v1/vehiculos/{}, patente={}", id, request.getPatente());
        return ResponseEntity.ok(vehiculoService.actualizar(id, request));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<VehiculoResponseDTO> cambiarEstado(
            @PathVariable @Positive Long id,
            @Valid @RequestBody EstadoRequestDTO request
    ) {
        log.info("Solicitud REST PATCH /api/v1/vehiculos/{}/estado, estado={}", id, request.getEstadoTramite());
        return ResponseEntity.ok(vehiculoService.cambiarEstado(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable @Positive Long id) {
        log.info("Solicitud REST DELETE /api/v1/vehiculos/{}", id);
        vehiculoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
