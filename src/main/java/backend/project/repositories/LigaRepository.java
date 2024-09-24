package backend.project.repositories;

import backend.project.entities.Liga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LigaRepository extends JpaRepository<Liga,Long> {
    List<Liga> findByNombre(String nombre);

    List<Liga> findByPais(String pais);
    @Query("SELECT l FROM Liga l ORDER BY l.nombre ASC")
    List<Liga> listarLigasResumen();
}
