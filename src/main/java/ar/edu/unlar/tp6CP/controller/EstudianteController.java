package ar.edu.unlar.tp6CP.controller;

import ar.edu.unlar.tp6CP.domain.Estudiante;
import ar.edu.unlar.tp6CP.exception.CriterioInvalidoException;
import ar.edu.unlar.tp6CP.exception.ErrorResponse;
import ar.edu.unlar.tp6CP.service.EstudianteService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;

@RestController
public class EstudianteController {
    private final EstudianteService service;

    public EstudianteController(EstudianteService service) {
        this.service = service;
    }

    @GetMapping("/api/estudiantes")
    public List<Estudiante> getEstudiantes(
            @RequestParam(defaultValue = "promedio") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {
        return service.ordenarEstudiantes(sortBy, order);
    }

    @RestControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(CriterioInvalidoException.class)
        public ResponseEntity<ErrorResponse> handleCriterioInvalido(CriterioInvalidoException ex) {
            ErrorResponse error = new ErrorResponse(
                    "Criterio de ordenamiento no válido",
                    ex.getCriterioRecibido(),
                    Arrays.asList("promedio", "edad", "nombre", "materiasAprobadas", "legajo")
            );
        return ResponseEntity.badRequest().body(error);
        }
    }
}
