package ar.edu.unlar.tp6CP.service;

import ar.edu.unlar.tp6CP.domain.Estudiante;
import ar.edu.unlar.tp6CP.repository.EstudianteRepository;
import ar.edu.unlar.tp6CP.exception.CriterioInvalidoException;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EstudianteService {
    private final EstudianteRepository repository;
    private final Map<String, Comparator<Estudiante>> estrategias = new HashMap<>();

    public EstudianteService(EstudianteRepository repository) {
        this.repository = repository;

        // Tie-breaker por defecto exigido por el enunciado
        Comparator<Estudiante> porLegajo = Comparator.comparing(Estudiante::getLegajo);

        // Registro de comparadores usando lambdas, method references y thenComparing
        estrategias.put("promedio", Comparator.comparing(Estudiante::getPromedio).thenComparing(porLegajo));
        estrategias.put("edad", Comparator.comparing(Estudiante::getEdad).thenComparing(porLegajo));
        estrategias.put("nombre", Comparator.comparing(Estudiante::getNombre).thenComparing(porLegajo));
        estrategias.put("materiasAprobadas", Comparator.comparing(Estudiante::getCantidadMateriasAprobadas).thenComparing(porLegajo));
        estrategias.put("legajo", porLegajo);
    }

    public List<Estudiante> ordenarEstudiantes(String sortBy, String order) {
        List<Estudiante> lista = repository.findAll();

        Comparator<Estudiante> comp = estrategias.get(sortBy);
        if (comp == null) {
            throw new CriterioInvalidoException(sortBy);
        }

        if ("desc".equalsIgnoreCase(order)) {
            comp = comp.reversed();
        }

        lista.sort(comp);
        return lista;
    }
}