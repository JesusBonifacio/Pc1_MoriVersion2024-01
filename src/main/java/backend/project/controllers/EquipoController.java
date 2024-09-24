package backend.project.controllers;

import backend.project.entities.Equipo;
import backend.project.services.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {
    @Autowired
    private EquipoService equipoService;

    // Listar equipos por liga ola
    @GetMapping("/{idLiga}")
    public ResponseEntity<List<Equipo>> listarEquiposPorLiga(@PathVariable Long idLiga) {
        List<Equipo> equipos = equipoService.listarEquiposPorLiga(idLiga);
        if (equipos != null && !equipos.isEmpty()) {
            return new ResponseEntity<>(equipos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
