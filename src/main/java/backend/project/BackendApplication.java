package backend.project;

import backend.project.entities.Equipo;
import backend.project.entities.Liga;
import backend.project.repositories.EquipoRepository;
import backend.project.repositories.LigaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;


@SpringBootApplication

public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner mappingDemo(
			LigaRepository ligaRepository,
			EquipoRepository equipoRepository

	) {
		return args -> {
			Liga liga1 = new Liga(null, "Liga 1", "Perú", "CONMEBOL", LocalDate.of(1900, 1, 1), LocalDate.of(1950, 1, 1), "Masculino", null);
			Liga liga2 = new Liga(null, "Liga 2", "Brasil", "CONMEBOL", LocalDate.of(1910, 2, 2), LocalDate.of(1960, 2, 2), "Femenino", null);
			ligaRepository.save(liga1);
			ligaRepository.save(liga2);

			equipoRepository.save(new Equipo(null, "Equipo 1", "E1", LocalDate.of(1950, 1, 1), 5, 1000, true, liga1));
			equipoRepository.save(new Equipo(null, "Equipo 2", "E2", LocalDate.of(1955, 2, 2), 3, 800, false, liga1));
			equipoRepository.save(new Equipo(null, "Equipo 3", "E3", LocalDate.of(1960, 3, 3), 4, 1200, true, liga1));

			equipoRepository.save(new Equipo(null, "Equipo A", "EA", LocalDate.of(1940, 4, 4), 7, 1500, false, liga2));
			equipoRepository.save(new Equipo(null, "Equipo B", "EB", LocalDate.of(1945, 5, 5), 2, 700, true, liga2));
			equipoRepository.save(new Equipo(null, "Equipo C", "EC", LocalDate.of(1950, 6, 6), 6, 1100, false, liga2));


		};
	}

}
