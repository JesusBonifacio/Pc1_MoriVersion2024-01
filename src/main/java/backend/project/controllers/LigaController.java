package backend.project.controllers;

import backend.project.entities.Liga;
import backend.project.services.LigaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/ligas")
//http://localhost:8082/api/ligas

public class LigaController {
    @Autowired
    private LigaService ligaService;
    @PostMapping
    public ResponseEntity<Liga> registrarLiga(@RequestBody Liga liga) {
        // Validar campos obligatorios
        if (liga.getNombre() == null || liga.getNombre().isEmpty() ||
                liga.getPais() == null || liga.getPais().isEmpty() ||
                liga.getConfederacion() == null || liga.getConfederacion().isEmpty() ||
                liga.getTipo() == null || liga.getTipo().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        // Registrar la liga
        Liga nuevaLiga = ligaService.registrarLiga(liga.getNombre(), liga.getPais(), liga.getConfederacion(),
                liga.getTipo(), liga.getFechaAfiliacion(), liga.getFechaFundacion());
        if (nuevaLiga == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        // Devolver la liga registrada
        return new ResponseEntity<>(nuevaLiga, HttpStatus.CREATED);
    }


    @DeleteMapping("/api/ligas/{id}")
    public ResponseEntity<String> eliminarLiga(@PathVariable Long id, @RequestParam(required = false) Boolean forced) {
        try {
            Liga liga = ligaService.buscarLigaPorId(id);

            // Si la liga no existe, devolver NOT_FOUND
            if (liga == null) {
                return new ResponseEntity<>("Liga con el Id: " + id + " no encontrada", HttpStatus.NOT_FOUND);
            }

            // Si no se fuerza la eliminación y la liga tiene equipos, devolver CONFLICT
            if (!forced && liga.getEquipos() != null && !liga.getEquipos().isEmpty()) {
                return new ResponseEntity<>("Liga con el Id: " + id + " aún tiene equipos registrados", HttpStatus.CONFLICT);
            }

            // Eliminar la liga
            ligaService.eliminarLiga(id, forced);
            return new ResponseEntity<>("Liga eliminada con éxito", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Ocurrió un error al eliminar la liga", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Buscar liga por ID
    @GetMapping("/{id}")
    public ResponseEntity<Liga> buscarLigaPorId(@PathVariable Long id) {
        Liga liga = ligaService.buscarLigaPorId(id);
        if (liga != null) {
            return new ResponseEntity<>(liga, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Listar todas las ligas
    @GetMapping
    public ResponseEntity<List<Liga>> listarLigas() {
        List<Liga> ligas = ligaService.listarLigas();
        return new ResponseEntity<>(ligas, HttpStatus.OK);
    }

    // Listar ligas por país
    @GetMapping("/pais")
    public ResponseEntity<List<Liga>> listarLigasPorPais(@RequestParam String pais) {
        List<Liga> ligas = ligaService.listarLigasPorPais(pais);
        return new ResponseEntity<>(ligas, HttpStatus.OK);
    }
}
