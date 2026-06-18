package ar.edu.unlar.tp6CP.repository;

import ar.edu.unlar.tp6CP.domain.Estudiante;
import java.util.ArrayList;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class EstudianteRepository {
    private List<Estudiante> estudiantes = new ArrayList<>();

    @PostConstruct
    public void init() {
        estudiantes = new ArrayList<>();
        // Agregar algunos estudiantes de ejemplo
        estudiantes.add(new Estudiante("LU-2024-001", "Martín Quiroga", 8.5, 22, 18));
        estudiantes.add(new Estudiante("LU-2024-002", "Valeria Díaz", 8.5, 20, 15)); // Empata promedio con Martín
        estudiantes.add(new Estudiante("LU-2024-003", "Facundo Castro", 7.2, 24, 22));
        estudiantes.add(new Estudiante("LU-2024-004", "Camila Torres", 9.1, 21, 24));
        estudiantes.add(new Estudiante("LU-2024-005", "Lucas González", 9.1, 23, 24)); // Empata promedio con Camila
        estudiantes.add(new Estudiante("LU-2024-006", "Agustina López", 6.8, 19, 10));
        estudiantes.add(new Estudiante("LU-2024-007", "Nahuel Herrera", 7.5, 22, 14)); // Empata edad con Martín
        estudiantes.add(new Estudiante("LU-2024-008", "Florencia Rios", 8.9, 25, 20));
        estudiantes.add(new Estudiante("LU-2024-009", "Tomás Sosa", 6.5, 20, 12));     // Empata edad con Valeria
        estudiantes.add(new Estudiante("LU-2024-010", "Lucía Fernández", 7.8, 21, 16)); // Empata edad con Camila
    }

    public List<Estudiante> findAll(){
        // devolver una copia de la lista para evitar modificaciones externas
        return new ArrayList<>(estudiantes);
    }
}