package cl.duoc.sigefi.vehiculos.dto;

import cl.duoc.sigefi.vehiculos.model.enums.EstadoTramite;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoRequestDTO {

    @NotNull(message = "El estado del trámite es obligatorio")
    private EstadoTramite estadoTramite;
}
