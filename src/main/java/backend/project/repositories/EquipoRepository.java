package backend.project.repositories;

import backend.project.entities.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    List<Equipo> findByLiga_Id(Long ligaId);
}
