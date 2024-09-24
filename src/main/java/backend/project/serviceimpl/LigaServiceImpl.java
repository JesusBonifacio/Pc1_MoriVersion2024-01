package backend.project.serviceimpl;

import backend.project.entities.Equipo;
import backend.project.entities.Liga;
import backend.project.repositories.EquipoRepository;
import backend.project.repositories.LigaRepository;
import backend.project.services.LigaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class LigaServiceImpl implements LigaService {
    @Autowired
    private LigaRepository ligaRepository;
    @Autowired
    private EquipoRepository equipoRepository;


    @Override
    public Liga registrarLiga(String nombre, String pais, String confederacion, String tipo, LocalDate fechaAfiliacion, LocalDate fechaFundacion) {
        // Verificar si ya existe una liga con el mismo nombre
        List<Liga> list = ligaRepository.findByNombre(nombre);
        if (!list.isEmpty()) {
            return null;  // Si ya existe una liga con ese nombre, retorna null
        }

        // Crear la liga nueva
        Liga liga = new Liga();
        liga.setNombre(nombre);
        liga.setPais(pais);
        liga.setConfederacion(confederacion);
        liga.setTipo(tipo);
        liga.setFechaAfiliacion(fechaAfiliacion);
        liga.setFechaFundacion(fechaFundacion);

        // Guardar en la base de datos y devolver la liga registrada
        return ligaRepository.save(liga);
    }

    @Override
    public void eliminarLiga(Long id, boolean forced) {
        Liga ligaEncontrada = ligaRepository.findById(id).orElse(null);
        if (ligaEncontrada != null) {
            if (forced) {
                // Forzar eliminación de equipos asociados
                for (Equipo equipo : ligaEncontrada.getEquipos()) {
                    equipo.setLiga(null);
                    equipoRepository.delete(equipo);
                }
                ligaEncontrada.setEquipos(null);
                ligaRepository.delete(ligaEncontrada);
            } else {
                // Eliminar solo si no tiene equipos
                if (ligaEncontrada.getEquipos().isEmpty()) {
                    ligaRepository.delete(ligaEncontrada);
                }
            }
        }
    }

    @Override
    public Liga buscarLigaPorId(Long id) {
        // Buscar una liga por su ID
        Liga liga = ligaRepository.findById(id).orElse(null);
        if (liga != null) {
            liga.setEquipos(null);  // Evitar cargar los equipos innecesariamente
        }
        return liga;
    }

    @Override
    public List<Liga> listarLigas() {
        // Listar todas las ligas
        List<Liga> ligas = ligaRepository.findAll();
        // Evitar cargar los equipos de cada liga
        for (Liga liga : ligas) {
            liga.setEquipos(null);
        }
        return ligas;
    }

    @Override
    public List<Liga> listarLigasPorPais(String pais) {
        // Listar ligas por país
        return ligaRepository.findByPais(pais);
    }
}
