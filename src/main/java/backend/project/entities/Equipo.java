package backend.project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "Equipos")
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String siglas;
    private LocalDate fechaFundacion;
    private int numeroCampeonatos;
    private int cantidadSocios;
    private boolean estadioPropio;

    @ManyToOne
    @JoinColumn(name = "liga_id")
    private Liga liga;
}
