package cl.duoc.sigefi.vehiculos.repository;

import cl.duoc.sigefi.vehiculos.model.entity.Vehiculo;
import cl.duoc.sigefi.vehiculos.model.enums.EstadoTramite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

    boolean existsByPatenteIgnoreCase(String patente);

    boolean existsByPatenteIgnoreCaseAndIdNot(String patente, Long id);

    List<Vehiculo> findByRutPropietarioIgnoreCase(String rutPropietario);

    List<Vehiculo> findByEstadoTramite(EstadoTramite estadoTramite);
}
