package backend.project.services;

import backend.project.entities.Equipo;

import java.util.List;

public interface EquipoService {
    List<Equipo> listarEquiposPorLiga(Long ligaId);
}
