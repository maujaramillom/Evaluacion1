package cl.duoc.sigefi.vehiculos.dto;

import cl.duoc.sigefi.vehiculos.model.enums.TipoVehiculo;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoRequestDTO {

    @NotBlank(message = "La patente es obligatoria")
    @Size(min = 5, max = 10, message = "La patente debe tener entre 5 y 10 caracteres")
    @Pattern(regexp = "^[A-Za-z0-9-]+$", message = "La patente solo puede contener letras, números y guion")
    private String patente;

    @NotBlank(message = "La marca es obligatoria")
    @Size(min = 2, max = 50, message = "La marca debe tener entre 2 y 50 caracteres")
    private String marca;

    @NotBlank(message = "El modelo es obligatorio")
    @Size(min = 1, max = 50, message = "El modelo debe tener entre 1 y 50 caracteres")
    private String modelo;

    @NotNull(message = "El año del vehículo es obligatorio")
    @Min(value = 1900, message = "El año no puede ser menor a 1900")
    @Max(value = 2100, message = "El año no puede ser mayor a 2100")
    private Integer anio;

    @NotNull(message = "El tipo de vehículo es obligatorio")
    private TipoVehiculo tipoVehiculo;

    @NotBlank(message = "El RUT del propietario es obligatorio")
    @Pattern(regexp = "^[0-9]{7,8}-[0-9Kk]$", message = "El RUT debe tener formato 12345678-9 o 12345678-K")
    private String rutPropietario;

    @NotBlank(message = "El nombre del propietario es obligatorio")
    @Size(min = 3, max = 120, message = "El nombre del propietario debe tener entre 3 y 120 caracteres")
    private String nombrePropietario;

    @NotBlank(message = "El país de origen es obligatorio")
    @Size(min = 2, max = 60, message = "El país de origen debe tener entre 2 y 60 caracteres")
    private String paisOrigen;

    @NotBlank(message = "El país de destino es obligatorio")
    @Size(min = 2, max = 60, message = "El país de destino debe tener entre 2 y 60 caracteres")
    private String paisDestino;
}
