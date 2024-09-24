package backend.project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "ligas")

public class Liga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String pais;
    private String confederacion;
    private LocalDate fechaAfiliacion;
    private LocalDate fechaFundacion;
    private String tipo;

    @OneToMany(mappedBy = "liga", fetch =FetchType.EAGER)
    private List<Equipo> equipos;
}
