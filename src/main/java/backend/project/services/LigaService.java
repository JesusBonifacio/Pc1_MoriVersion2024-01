package backend.project.services;

import backend.project.entities.Liga;

import java.time.LocalDate;
import java.util.List;


public interface LigaService {
    public Liga registrarLiga(String nombre, String pais, String confederacion, String tipo, LocalDate fechaAfiliacion,LocalDate fechaFundacion);

    public void eliminarLiga(Long id, boolean forced);

    public Liga buscarLigaPorId(Long id);

    public List<Liga> listarLigas();

    public List<Liga> listarLigasPorPais(String pais);
}
