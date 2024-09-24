package backend.project.serviceimpl;

import backend.project.entities.Equipo;
import backend.project.repositories.EquipoRepository;
import backend.project.services.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoServiceImpl implements EquipoService {
    @Autowired
    private EquipoRepository equipoRepository;

    @Override
    public List<Equipo> listarEquiposPorLiga(Long ligaId) {
        return  equipoRepository.findByLiga_Id(ligaId);
    }

}
